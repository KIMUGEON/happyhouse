<template>
  <b-row class="mt-4 mb-4 text-center">
    <b-col class="sm-6">
      <b-form-input
        v-model.lazy="YYYYMM"
        placeholder="YYYYMM ex)202110"
        style="border: 5px solid black"
      >
      </b-form-input>
    </b-col>
    <b-button type="button" variant="outline-secondary" @click="setDealYmd()">
      년월설정
    </b-button>

    <b-col class="sm-3">
      <b-form-select
        v-model="sidoCode"
        :options="sidos"
        @change="gugunList"
      ></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select
        v-model="gugunCode"
        :options="guguns"
        @change="dongList"
      ></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select
        v-model="dongCode"
        :options="dongs"
        @change="searchApt"
      ></b-form-select>
    </b-col>
  </b-row>
</template>

<script>
// @store.houseStore.state.DEAL_YMD
import { mapState, mapActions, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      YYYYMM: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses", "DEAL_YMD"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
      "getCctvList",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "SET_DEAL_YMD",
      "CLEAR_HOUSES_LIST",
    ]),
    setDealYmd() {
      this.SET_DEAL_YMD(this.YYYYMM);
      console.log(this.YYYYMM);
      console.log("컴포넌트에서 호출 년월설정" + this.DEAL_YMD);
    },
    // setDealYmd() {
    //   return this.$store.state.houseStore.DEAL_YMD;
    // },
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      console.log(this.gugunCode);
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    searchApt() {
      if (this.dongCode) {
        console.log("dongcode : " + this.dongCode.substr(5, 10));
        console.log("search아파트에서 호출" + this.DEAL_YMD);
        this.CLEAR_HOUSES_LIST();
        this.getHouseList([
          this.DEAL_YMD,
          this.dongCode.substr(0, 5),
          this.dongCode.substr(5, 10),
        ]);
      }
    },
  },
};
</script>

<style></style>
