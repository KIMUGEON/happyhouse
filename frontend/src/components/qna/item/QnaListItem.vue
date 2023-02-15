<template>
  <b-tr>
    <b-td><b-icon icon="book" /></b-td>
    <b-th class="text-left">
      <router-link
        style="color: black"
        :to="{ name: 'qnaDetail', params: { articleno: articleno } }"
        >{{ subject }}</router-link
      >
    </b-th>
    <b-td>{{ hit }}</b-td>
    <b-td>{{ user_id }}</b-td>
    <b-td>{{ regtime | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "QnaListItem",
  props: {
    articleno: Number,
    user_id: String,
    subject: String,
    hit: Number,
    regtime: String,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
