<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >
            <b-form-group label="아이디:" label-for="user_id">
              <b-form-input
                id="user_id"
                v-model="$v.user.user_id.$model"
                :state="validateState('user_id')"
                required
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
              ></b-form-input>

              <b-form-invalid-feedback id="input-live-feedback">
                ID는 3글자 이상입니다
              </b-form-invalid-feedback>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="user_password">
              <b-form-input
                type="password"
                id="user_password"
                v-model="user.user_password"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름:" label-for="user_name">
              <b-form-input
                id="user_name"
                v-model="user.user_name"
                required
                placeholder="이름 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이메일:" label-for="user_email">
              <b-form-input
                id="user_email"
                v-model="user.user_email"
                required
                placeholder="이메일 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <!-- <b-form-group label="전화번호:" label-for="user_phone">
              <b-form-input
                id="user_phone"
                v-model="user.user_phone"
                required
                placeholder="전화번호 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group> -->

            <b-button
              type="button"
              variant="primary"
              class="m-1"
              @click="onSubmit"
              >가입하기</b-button
            >
            <b-button
              type="button"
              variant="success"
              class="m-1"
              @click="onReset"
              >리셋</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
    <br /><br /><br /><br /><br /><br />
  </b-container>
</template>

<script>
import { jointUser } from "@/api/member";
import { validationMixin } from "vuelidate";
import { required, minLength } from "vuelidate/lib/validators";
export default {
  name: "UserRegist",
  mixins: [validationMixin],
  data() {
    return {
      user: {
        user_id: "",
        user_password: "",
        user_name: "",
        user_email: "",
      },
    };
  },
  props: {
    type: { type: String },
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
      else this.registUser();
    },
    onReset(event) {
      event.preventDefault();
      this.user.user_id = "";
      this.user.user_password = "";
      this.user.user_name = "";
      this.user.user_email = "";
      //  this.$router.push({ name: "qnaList" });
    },
    registUser() {
      jointUser(
        {
          user_id: this.user.user_id,
          user_password: this.user.user_password,
          user_name: this.user.user_name,
          user_email: this.user.user_email,
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "idcheck") {
            msg = "이미 사용중인 아이디입니다.";
          }
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.$router.push({ name: "home" });
        },
        (error) => {
          console.log(error);
        },
      );
    },
    validateState(user_id) {
      const { $dirty, $error } = this.$v.user[user_id];
      return $dirty ? !$error : null;
    },
  },
};
</script>

<style></style>
