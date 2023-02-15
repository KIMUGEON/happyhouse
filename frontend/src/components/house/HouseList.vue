<template>
  <div>
    <b-container
      v-if="houses && houses.length != 0"
      class="bv-example-row mt-3"
    >
      <div>
        <b-button
          type="button"
          @click="setting"
          block
          variant="outline-secondary"
        >
          검색
        </b-button>
        <b-table
          class="aa"
          :items="apts"
          :fields="fields"
          :per-page="perPage"
          :current-page="currentPage"
          id="my-table"
          @row-clicked="addInterest"
        >
          <template #cell(actions)="row">
            <b-button
              size="sm"
              @click="info(row.item, row.index, $event.target)"
              class="mr-1"
              variant="outline-primary"
            >
              Info modal
            </b-button>
          </template>
        </b-table>
        <b-pagination
          page-class="customPage"
          align="center"
          v-model="currentPage"
          :total-rows="rows"
          :per-page="perPage"
          aria-controls="my-table"
        ></b-pagination>
        <b-modal
          :id="infoModal.id"
          :title="infoModal.title"
          ok-only
          @hide="resetInfoModal"
        >
          <pre>{{ infoModal.content }}</pre>
        </b-modal>
        <!-- <b-table
          id="my-table"
          :items="item"
          :per-page="perPage"
          :current-page="currentPage"
          small
        ></b-table> -->
        <p class="mt-3" align="center">Total Count: {{ totalCount }}</p>
      </div>
      <!-- <house-list-item
        v-for="(house, index) in houses"
        :key="index"
        :house="house"
        :page="1"
      /> -->
    </b-container>
    <b-container v-else class="bv-example-row mt-3">
      <b-row>
        <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
// import HouseListItem from "@/components/house/HouseListItem.vue";
import { mapState, mapActions, mapMutations, mapGetters } from "vuex";
const houseStore = "houseStore";
// const convert = require("xml-js");
export default {
  name: "HouseList",
  components: {
    // HouseListItem,
  },
  data() {
    return {
      perPage: 10,
      currentPage: 1,
      apts: this.houses,
      fields: [
        {
          key: "도로명",
          label: "도로명",
        },
        {
          key: "법정동",
          label: "법정동",
        },
        {
          key: "아파트",
          label: "아파트명",
        },
        {
          key: "거래금액",
          label: "거래금액",
        },
        { key: "actions", label: "Actions" },
      ],
      infoModal: {
        id: "info-modal",
        title: "",
        content: "",
      },
      interdata: this.interest,
    };
  },
  // created() {
  //   console.log(this.totalCount);
  // },
  methods: {
    colorChange(flag) {
      this.isColor = flag;
    },
    info(item, index, button) {
      this.infoModal.title = `Row index: ${index}`;
      this.infoModal.content = JSON.stringify(item, null, 2);
      this.$root.$emit("bv::show::modal", this.infoModal.id, button);
    },
    resetInfoModal() {
      this.infoModal.title = "";
      this.infoModal.content = "";
    },
    setting() {
      this.apts = this.houses;
    },
    addInterest(e) {
      // var tempdata = {
      //   aptname: e.아파트,
      //   doro: e.도로명,
      //   dong: e.법정동,
      // };
      console.log(typeof e);
      // console.log(tempdata);
      // this.SET_INTEREST(e.거래금액);
      // this.$store.commit(houseStore, "SET_INTEREST");
      this.$store.commit("houseStore/SET_INTEREST", e);
      // this.SET_INTEREST(e);
      console.log(e);
      console.log(JSON.stringify(this.interest)); // this.interest = tempdata;
      // console.log(e.거래금액);
      // this.$houseStore.commit("SET_INTEREST", e);
      // console.log(this.$houseStore.state.interest);
      // console.log(e);
      // console.log(this.interdata);
      // this.SET_INTEREST({
      //   aptname: e.아파트,
      //   doro: e.도로명,
      //   dong: e.법정동,
      // });
      // console.log(this.interest);

      // this.interest = this.SET_INTEREST(JSON.stringify(this.interdata));
      // console.log(this.interest);
      // var changedata = convert.json2xml(interdata, {
      //   compact: true,
      //   spaces: 4,
      // });
      // console.log(changedata);
      // // let interestdata = JSON.parse(e);
      // // console.log(interestdata);
      // this.SET_INTEREST(interdata);
      // // console.log(interestdata);
      // console.log(this.interest);
    },
    // linkGen(pageNum) {
    // console.log(this.pageNo);
    // this.SET_PAGE_NO(pageNum);
    // this.pageNo = pageNum;
    // return pageNum === 1 ? "?" : (this.pageNo = this.pageNum);
    // this.$store.dispatch("getHouse", this.house);
    //  return pageNum === 1 ? '?' : `?page=${pageNum}`;
    // },
  },
  computed: {
    ...mapState(houseStore, [
      "house",
      "houses",
      "totalCount",
      "pageNo",
      "interest",
    ]),
    ...mapActions(houseStore, ["getHouseList"]),
    ...mapMutations(houseStore, ["SET_PAGE_NO", "SET_INTEREST"]),
    ...mapGetters(houseStore, ["reginterest"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
    rows() {
      // console.log(this.houses);
      console.log(this.houses.length);
      // console.log(sessionStorage.getItem("vuex"));
      // console.log(this.houses[1].지번);
      console.log(this.apts);
      console.log(this.houses);

      return this.houses.length;
    },
  },
};
</script>

<style>
.aa > tbody :hover {
  background-color: lightblue;
}

.customPage.page-item.active .page-link {
  background-color: rgb(173, 180, 183);
  border-color: rgb(173, 180, 183);
  color: rgb(0, 0, 0);
}
</style>
