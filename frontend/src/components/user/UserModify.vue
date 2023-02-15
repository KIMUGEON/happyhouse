<template>
  <div class="inner">
    <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
      <b-form v-if="show">
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
            v-model="user.user_id"
            disabled
            type="user_id"
            placeholder="Enter ID"
            required
            @keyup.enter="confirm"
          ></b-form-input>
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
            required
            type="password"
            @keyup.enter="confirm"
          ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-3" label="Your Name:" label-for="input-3">
          <b-form-input
            id="input-3"
            v-model="user.user_name"
            placeholder="Enter Password"
            required
            @keyup.enter="confirm"
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="input-group-4"
          label="Your Email:"
          label-for="input-4"
        >
          <b-form-input
            id="input-4"
            v-model="user.user_email"
            placeholder="Enter Email"
            required
            @keyup.enter="confirm"
          ></b-form-input>
        </b-form-group>
        <br />
        <b-button type="button" variant="primary" @click="onSubmit"
          >Submit</b-button
        >
        <b-button type="button" variant="danger" @click="onReset"
          >Reset</b-button
        >
      </b-form>
    </b-card>
  </div>
</template>

<script>
import { modifyUser } from "@/api/member";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserModify",
  data() {
    return {
      user: {
        user_id: "",
        user_password: "",
        user_name: "",
        user_email: "",
      },
      show: true,
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.user.user_id = this.userInfo.user_id;
    this.user.user_password = this.userInfo.user_password;
    this.user.user_name = this.userInfo.user_name;
    this.user.user_email = this.userInfo.user_email;
  },
  methods: {
    ...mapActions(memberStore, ["getUserInfo"]),
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.user.user_id &&
        ((msg = "아이디를 입력해주세요"),
        (err = false),
        this.$refs.user_id.focus());
      err &&
        !this.user.user_password &&
        ((msg = "비밀번호를 입력해주세요"),
        (err = false),
        this.$refs.user_password.focus());
      err &&
        !this.user.user_name &&
        ((msg = "이름을 입력해주세요"),
        (err = false),
        this.$refs.user_name.focus());
      err &&
        !this.user.user_email &&
        ((msg = "이메일을 입력해주세요"),
        (err = false),
        this.$refs.user_email.focus());

      if (!err) alert(msg);
      else this.modifyUser();
    },
    modifyUser() {
      modifyUser(
        {
          user_id: this.user.user_id,
          user_password: this.user.user_password,
          user_name: this.user.user_name,
          user_email: this.user.user_email,
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";

            let token = sessionStorage.getItem("access-token");
            this.getUserInfo(token);
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "home" });
        },
        (error) => {
          console.log(error);
        },
      );
    },
    onReset(event) {
      event.preventDefault();
      this.user.user_id = "";
      this.user.user_password = "";
      this.user.user_name = "";
      this.user.user_email = "";
      //  this.$router.push({ name: "qnaList" });
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
