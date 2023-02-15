<template>
  <div>
    <section
      class="bg-title-page p-t-40 p-b-50 flex-col-c-m"
      style="background-image: url(https://picsum.photos/1920/239/?image=864)"
    >
      <h2 class="l-text2 t-center">House deal</h2>
    </section>

    <section class="cart bgwhite p-t-70 p-b-100">
      <div class="container">
        <!-- Cart item -->
        <kakao-map />
        <house-search-bar></house-search-bar>

        <house-list />

        <div
          class="flex-w flex-sb-m p-t-25 p-b-25 bo8 p-l-35 p-r-60 p-lr-15-sm"
        ></div>

        <!-- Total -->
      </div>
    </section>
  </div>
</template>
<script>
// import CartList from "@/components/features/CartList.vue";
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import KakaoMap from "@/components/house/KakaoMap.vue";

export default {
  methods: {
    clearCart() {
      this.$store.dispatch("cart/clearCart");
    },
  },
  components: {
    HouseSearchBar,
    // CartList,
    HouseList,
    KakaoMap,
  },
};
</script>
