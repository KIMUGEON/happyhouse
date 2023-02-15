<template>
  <b-tr>
    <b-td>{{ user_id }}</b-td>
    <b-td>{{ user_password }}</b-td>
    <b-td>{{ user_name }}</b-td>
    <b-td>{{ user_email }}</b-td>
    <b-td @click="deleteUser"><b-button>삭제</b-button></b-td>
  </b-tr>
</template>

<script>
import { deleteUser } from "@/api/member";
export default {
  name: "UserListItem",
  props: {
    user_id: String,
    user_password: String,
    user_name: String,
    user_email: String,
    joindate: String,
  },

  methods: {
    deleteUser() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        deleteUser(this.user_id, () => {
          this.$router.push({ name: "home" });
        });
      }
    },
  },
};
</script>

<style></style>
