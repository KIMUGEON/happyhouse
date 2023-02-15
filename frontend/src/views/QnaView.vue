<template>
  <div>
    <section
      class="bg-title-page p-t-50 p-b-40 flex-col-c-m"
      :style="'background-image: url(https://picsum.photos/1920/239/?image=520);'"
    >
      <h2 class="l-text2 t-center" style="color: white">Q&A Board</h2>
      <p class="m-text13 t-center">환영합니다</p>
    </section>
    <b-container class="bv-example-row mt-3 text-center">
      <router-view></router-view>
    </b-container>
  </div>
</template>
<script>
// import { mapState } from "vuex";

export default {
  name: "QnaView",

  computed: {},
  methods: {},
  components: {},
};
</script>
