<template>
  <div class="header1">
    <!-- Header desktop -->
    <div class="container-menu-header">
      <div class="topbar">
        <div class="topbar-social">
          <a
            href="https://facebook.com"
            target="_blank"
            class="topbar-social-item fa fa-facebook"
          ></a>
          <a href="#" class="topbar-social-item fa fa-instagram"></a>
          <a href="#" class="topbar-social-item fa fa-pinterest-p"></a>
          <a href="#" class="topbar-social-item fa fa-snapchat-ghost"></a>
          <a href="#" class="topbar-social-item fa fa-youtube-play"></a>
        </div>

        <span class="topbar-child1"> </span>

        <div class="topbar-child2">
          <span class="topbar-email"> With.ssafy </span>

          <div class="topbar-language rs1-select2"></div>
        </div>
      </div>

      <div class="wrap_header">
        <!-- Logo -->
        <router-link to="/"
          ><b-img
            :src="require('@/assets/mainlogo.png')"
            class="logo"
            alt="IMG-LOGO"
            height="70px"
          />
        </router-link>

        <!-- Menu -->
        <div class="wrap_menu">
          <nav class="menu">
            <ul class="main_menu">
              <router-link
                :to="{ name: 'home' }"
                tag="li"
                active-class="sale-noti"
                exact
              >
                <a>Home</a>
              </router-link>

              <router-link
                :to="{ name: 'qna' }"
                tag="li"
                active-class="sale-noti"
                exact
              >
                <a>Q&A</a>
              </router-link>

              <router-link
                :to="{ name: 'news' }"
                tag="li"
                active-class="sale-not"
                exact
              >
                <a>NEWS</a>
              </router-link>

              <router-link
                :to="{ name: 'house' }"
                tag="li"
                active-class="sale-not"
                exact
              >
                <a>아파트 거래정보</a>
              </router-link>
            </ul>
          </nav>
        </div>

        <!-- <b-navbar-nav class="ml-auto" v-if="userInfo">
          <b-nav-item-dropdown right>
            <template #button-content>
              <em
                >{{ userInfo.user_name }}({{ userInfo.user_id }})님
                환영합니다.</em
              >
            </template>
            <b-dropdown-item
              ><router-link
                :to="{ name: 'mypage', params: { user_id: userInfo.user_id } }"
                >Profile</router-link
              ></b-dropdown-item
            >
            <b-dropdown-item @click="onClickLogout">로그아웃</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav> -->

        <!-- Header Icon -->
        <div class="header-icons">
          <b-navbar-nav class="ml-auto" v-if="userInfo">
            <b-nav-item-dropdown right>
              <!-- Using 'button-content' slot -->
              <template #button-content>
                <em style="color: black; font-weight: 1000"
                  >{{ userInfo.user_name }}({{ userInfo.user_id }})님
                  환영합니다.</em
                >
              </template>

              <b-dropdown-item
                ><router-link
                  style="color: black"
                  :to="{
                    name: 'mypage',
                    params: { user_id: userInfo.user_id },
                  }"
                  >Profile</router-link
                ></b-dropdown-item
              >
              <b-dropdown-item v-if="isAdmin"
                ><router-link :to="{ name: 'list' }"
                  >회원 관리</router-link
                ></b-dropdown-item
              >
              <b-dropdown-item @click="onClickLogout">로그아웃</b-dropdown-item>
            </b-nav-item-dropdown>
          </b-navbar-nav>
          <b-navbar-nav class="ml-auto" v-else>
            <b-nav-item-dropdown right>
              <!-- Using 'button-content' slot -->
              <template #button-content>
                <a href="#" class="header-wrapicon1 dis-block">
                  <img
                    src="images/icons/icon-header-01.png"
                    class="header-icon1"
                    alt="ICON"
                  />
                </a>
              </template>
              <b-dropdown-item
                ><router-link
                  style="cursor: pointer"
                  :to="{ name: 'regist' }"
                  tag="li"
                  active-class="sale-noti"
                  exact
                >
                  회원가입
                </router-link></b-dropdown-item
              >
              <b-dropdown-item
                ><router-link
                  style="cursor: pointer"
                  :to="{ name: 'login' }"
                  tag="li"
                  active-class="sale-noti"
                  exact
                >
                  로그인
                </router-link></b-dropdown-item
              >
            </b-nav-item-dropdown>
          </b-navbar-nav>

          <span class="linedivide1"></span>
          <div class="header-wrapicon2" v-if="userInfo">
            <router-link
              style="cursor: pointer"
              :to="{ name: 'interest' }"
              tag="li"
              active-class="sale-noti"
              exact
            >
              관심목록
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo", "isAdmin"]),
  },
  methods: {
    ...mapMutations(memberStore, [
      "SET_IS_LOGIN",
      "SET_USER_INFO",
      "SET_IS_ADMIN",
    ]),
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      this.SET_IS_ADMIN(false);
      sessionStorage.removeItem("access-token");
      alert("로그아웃되었습니다");
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
  },
};
</script>
<style scoped></style>
