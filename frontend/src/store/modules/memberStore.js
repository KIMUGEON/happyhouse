import jwt_decode from "jwt-decode";
import { login } from "@/api/member.js";
import { findById } from "../../api/member";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    isAdmin: false,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
      console.log(state.isLogin);
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.userInfo = userInfo;
    },
    SET_IS_ADMIN: (state, isAdmin) => {
      state.isAdmin = isAdmin;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {},
      );
    },
    getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      findById(
        decode_token.user_id,
        (response) => {
          if (response.data.message === "admin") {
            commit("SET_IS_ADMIN", true);
            commit("SET_USER_INFO", response.data.userInfo);
          } else if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};

export default memberStore;
