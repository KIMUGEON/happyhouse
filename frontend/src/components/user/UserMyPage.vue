<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>My Page</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="5">
        <b-card>
          <b-img
            :src="require('@/assets/user.jpg')"
            style="height: 280px"
          ></b-img>
        </b-card>
      </b-col>
      <b-col cols="7">
        <b-card>
          <template #header><h5 style="font-weight: 1000">내정보</h5></template>

          <hr class="my-4" />

          <b-container class="mt-4">
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">아이디</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.user_id }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이름</b-col
              ><b-col cols="4" align-self="start">{{
                userInfo.user_name
              }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이메일</b-col
              ><b-col cols="4" align-self="start">{{
                userInfo.user_email
              }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">가입일</b-col
              ><b-col cols="4" align-self="start">{{
                userInfo.joindate
              }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
          </b-container>
          <hr class="my-4" />

          <router-link
            :to="{
              name: 'userModify',
              params: { user_id: userInfo.user_id },
            }"
            ><b-button variant="primary" class="mr-1"
              >정보수정</b-button
            ></router-link
          >

          <b-button variant="danger" @click="deleteUser">회원탈퇴</b-button>
        </b-card>
        <br />
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapMutations, mapState } from "vuex";
import { getUser, deleteUser } from "@/api/member";
const memberStore = "memberStore";

export default {
  name: "UserMyPage",
  data() {
    return {
      user: {
        user_id: "",
        user_password: "",
      },
      show: true,
    };
  },
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo", "isLogin", "isLoginError"]),
  },
  created() {
    getUser(
      this.$route.params.user_id,
      (response) => {
        this.user = response.data;
      },
      (error) => {
        console.log("삭제시 에러발생!!", error);
      },
    );
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    deleteUser() {
      if (confirm("정말로 회원 탈퇴하시겠습니까?")) {
        deleteUser(this.userInfo.user_id, () => {
          this.SET_IS_LOGIN(false);
          this.SET_USER_INFO(null);
          sessionStorage.removeItem("access-token");
          this.$router.push({ name: "home" });
        });
      }
    },
  },
};
</script>

<style></style>
