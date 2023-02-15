<template>
  <div class="inner">
    <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
      <b-form @reset="onReset" v-if="show">
        <b-alert show variant="danger" v-if="isLoginError"
          >아이디 또는 비밀번호를 확인하세요.</b-alert
        >
        <b-form-group
          id="input-group-1"
          label="Your ID:"
          label-for="input-1"
          description="We'll never share your email with anyone else."
        >
          <b-form-input
            id="input-1"
            v-model="$v.user.user_id.$model"
            :state="validateState('user_id')"
            type="user_id"
            placeholder="Enter ID"
            required
            @keyup.enter="confirm"
          ></b-form-input>

          <b-form-invalid-feedback id="input-live-feedback">
            ID는 3글자 이상입니다
          </b-form-invalid-feedback>
        </b-form-group>

        <b-form-group
          id="input-group-2"
          label="Your Password:"
          label-for="input-2"
        >
          <b-form-input
            id="input-2"
            v-model="user.user_password"
            placeholder="Enter Password"
            type="password"
            required
            @keyup.enter="confirm"
          ></b-form-input>
        </b-form-group>
        <br />
        <b-button variant="primary" @click="confirm">Submit</b-button>
        <b-button type="reset" variant="danger">Reset</b-button>

        <br />
        <br />
        <router-link to="/user/regist" class="link">| 회원가입 | </router-link>
      </b-form>
      <br />
    </b-card>
    <br /><br /><br /><br /><br /><br />
  </div>
</template>

<script>
import { validationMixin } from "vuelidate";
import { required, minLength } from "vuelidate/lib/validators";

import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserLogin",
  mixins: [validationMixin],

  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
    //nameState() {
    //  return this.user.user_id.length > 4 ? true : false;
    //},
  },
  data() {
    return {
      user: {
        user_id: "",
        user_password: "",
      },
      show: true,
    };
  },
  validations: {
    user: {
      user_id: {
        required,
        minLength: minLength(3),
      },
    },
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        alert("환영합니다");
        this.$router.push({ name: "home" });
        this.$router.go({ name: "home" });
      } else {
        alert(
          "아이디(로그인 전용 아이디) 또는 비밀번호를 잘못 입력했습니다.       입력하신 내용을 다시 확인해주세요.",
        );
      }
    },
    movePage() {
      this.$router.push({ name: "regist" });
    },
    validateState(user_id) {
      const { $dirty, $error } = this.$v.user[user_id];
      return $dirty ? !$error : null;
    },

    onReset(event) {
      event.preventDefault();
      // Reset our form values
      this.user.user_id = "";
      this.user.user_password = "";
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
  },
};
</script>

<style>
.inner {
  position: relative;
  left: 50%;
  transform: translateX(-50%);
  width: 500px;
}
.link {
  color: rgb(114, 104, 104);
}
</style>
