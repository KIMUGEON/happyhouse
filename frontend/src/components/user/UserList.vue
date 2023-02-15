<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <br />
        <div><h3>회원 목록</h3></div>
        <br />
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="users.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="white">
            <b-tr>
              <b-th>아이디</b-th>
              <b-th>비밀번호</b-th>
              <b-th>이름</b-th>
              <b-th>이메일</b-th>
              <b-th></b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <user-list-item
              v-for="user in users"
              :key="user.user_id"
              v-bind="user"
            />
          </tbody>
        </b-table-simple>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>
    <br /><br /><br />
  </b-container>
</template>

<script>
// import http from "@/api/http";
import { listUser } from "@/api/member.js";

import UserListItem from "@/components/user/UserListItem.vue";

export default {
  name: "UserList",
  components: {
    UserListItem,
  },
  data() {
    return {
      users: [],
    };
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listUser(
      param,
      (response) => {
        this.users = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "qnaRegister" });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
