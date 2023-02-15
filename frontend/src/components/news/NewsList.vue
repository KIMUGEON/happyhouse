<template>
  <b-card class="overflow-auto">
    <b-low
      ><p style="font-weight: bold">
        <b-icon icon="newspaper" /> 주요 뉴스
      </p></b-low
    >
    <b-row>
      <b-col v-if="articles.length">
        <b-table-simple hover responsive>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <news-list-item
              v-for="article in articles"
              :key="article.title"
              v-bind="article"
            />
          </tbody>
        </b-table-simple>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>
  </b-card>
</template>

<script>
// import http from "@/api/http";
import { listNews } from "@/api/news.js";
import NewsListItem from "@/components/news/NewsListItem";

import Vue from "vue";

import { BootstrapVue, BootstrapVueIcons } from "bootstrap-vue";
Vue.use(BootstrapVue);
Vue.use(BootstrapVueIcons);

export default {
  name: "NewsList",
  components: {
    NewsListItem,
  },
  data() {
    return {
      articles: [],
    };
  },
  created() {
    listNews(
      (response) => {
        this.articles = response.data;
        console.log(response.data);
      },
      (error) => {
        console.log(error);
      },
    );
  },
  computed: {
    rows() {
      return this.articles.length;
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
