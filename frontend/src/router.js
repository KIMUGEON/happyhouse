import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "./views/HomeView.vue";
import QnaView from "./views/QnaView.vue";
import HouseView from "./views/HouseView";
import store from "@/store/index.js";
import NewsView from "./views/NewsView";
Vue.use(VueRouter);

const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");

  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo == null) {
    alert("로그인이 필요한 페이지입니다..");
    next({ name: "login" });
    // router.push({ name: "signIn" });
  } else {
    // console.log("로그인 했다.");
    next();
  }
};

export default new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/qna",
      name: "qna",
      component: QnaView,
      redirect: "/qna/list",
      children: [
        {
          path: "list",
          name: "qnaList",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/qna/QnaList.vue"),
        },
        {
          path: "write",
          name: "qnaRegister",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/qna/QnaRegister.vue"),
        },
        {
          path: "detail/:articleno",
          name: "qnaDetail",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/qna/QnaDetail.vue"),
        },
        {
          path: "modify/:articleno",
          name: "qnaModify",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/qna/QnaModify.vue"),
        },
        {
          path: "delete/:articleno",
          name: "qnaDelete",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/qna/QnaDelete.vue"),
        },
      ],
    },
    {
      path: "/interest",
      name: "interest",
      beforeEnter: onlyAuthUser,
      component: () => import("@/views/InterestView.vue"),
    },
    {
      path: "/house",
      name: "house",
      beforeEnter: onlyAuthUser,
      component: HouseView,
    },
    {
      path: "/news",
      name: "news",
      beforeEnter: onlyAuthUser,
      component: NewsView,
    },
    {
      path: "/user",
      name: "user",
      component: () => import("@/views/UserView.vue"),
      children: [
        {
          path: "login",
          name: "login",
          component: () => import("@/components/user/UserLogin.vue"),
        },
        {
          path: "regist",
          name: "regist",
          component: () => import("@/components/user/UserRegist.vue"),
        },
        {
          path: "mypage/:user_id",
          name: "mypage",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/user/UserMyPage.vue"),
        },
        {
          path: "modify/:user_id",
          name: "userModify",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/user/UserModify.vue"),
        },
        {
          path: "list",
          name: "list",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/user/UserList.vue"),
        },
        {
          path: "detail/:user_id",
          name: "detail",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/user/UserList.vue"),
        },
      ],
    },
  ],
});
