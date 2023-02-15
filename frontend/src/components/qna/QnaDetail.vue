<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>
          ${article.subject} [${article.hit}]</h3><div><h6>${article.user_id}</div><div>${article.regtime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// import moment from "moment";
// import http from "@/api/http";
import { mapState } from "vuex";
import { getArticle, deleteArticle } from "@/api/board";
const memberStore = "memberStore";
export default {
  name: "QnaDetail",
  data() {
    return {
      article: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo", "isAdmin"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    getArticle(
      this.$route.params.articleno,
      (response) => {
        this.article = response.data;
      },
      (error) => {
        console.log("삭제시 에러발생!!", error);
      },
    );
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "qnaList" });
    },
    moveModifyArticle() {
      if (this.article.user_id != this.userInfo.user_id && !this.isAdmin) {
        alert("본인만 수정 가능합니다");
      } else {
        this.$router.replace({
          name: "qnaModify",
          params: { articleno: this.article.articleno },
        });
      }
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (this.article.user_id != this.userInfo.user_id && !this.isAdmin) {
        alert("본인만 삭제 가능합니다");
      } else {
        if (confirm("정말로 삭제?")) {
          deleteArticle(this.article.articleno, () => {
            this.$router.push({ name: "qnaList" });
          });
        }
      }
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>
