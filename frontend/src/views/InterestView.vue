<template>
  <b-container class="bv-example-row mt-3 text-center">
    <section
      class="bg-title-page p-t-40 p-b-50 flex-col-c-m"
      style="background-image: url(https://picsum.photos/1920/239/?image=505)"
    >
      <h2 class="l-text2 t-center">관심목록</h2>
    </section>
    <br />
    <br />
    <h3 class="underline-orange">
      <b-icon icon="house-fill"></b-icon> 매물 검색 기록
    </h3>
    <b-row>
      <b-col> </b-col>
    </b-row>
    <b-row>
      <b-col cols="6" align="left">
        <interest-list />
      </b-col>
      <b-col cols="6">
        <br />
        <interest-map />
      </b-col>
    </b-row>
  </b-container>
</template>
<script>
// import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import InterestList from "@/components/house/InterestList.vue";
import InterestMap from "@/components/house/InterestMap.vue";

export default {
  methods: {},
  components: {
    // HouseSearchBar,
    // CartList,
    InterestList,
    InterestMap,
  },
  created() {},
};
</script>
