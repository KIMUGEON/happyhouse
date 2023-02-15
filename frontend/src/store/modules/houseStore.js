import {
  sidoList,
  gugunList,
  dongList,
  houseList,
  cctvList,
} from "@/api/house.js";
// import axios from "axios";
var convert = require("xml-js");
const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    DEAL_YMDS: [{ value: null, text: "선택하세요" }],
    cctvs: [],
    houses: [],
    house: null,
    pageNo: 1, //페이지번호
    totalCount: 500, //총 매물 갯수
    DEAL_YMD: 202110,
    interest: [],
  },

  getters: {},

  mutations: {
    SET_INTEREST: (state, data) => {
      console.log("set interest 뮤테이션 호출?");
      // console.log(state, data);
      // state.interest += data;
      state.interest.push(data);
      console.log(state.interest);
    },
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_HOUSES_LIST: (state) => {
      state.houses = [];
    },
    SET_HOUSES_LIST: (state, houses) => {
      //   console.log(houses);
      state.houses = houses;
    },
    SET_HOUSE_LIST: (state, house) => {
      //   console.log(houses);
      state.house = house;
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
    },
    SET_TOTAL_COUNT: (state, totalCount) => {
      state.totalCount = totalCount;
    },
    SET_PAGE_NO: (state, pageNo) => {
      state.pageNo = pageNo;
      console.log("페이지번호 변경완료" + state.pageNo);
    },
    SET_DEAL_YMD: (state, DEAL_YMD) => {
      state.DEAL_YMD = DEAL_YMD;
      console.log("mutations 호출 " + state.DEAL_YMD);
      // console.log(" : " + state.DEAL_YMD);
    },
    SET_CCTV_LIST: (state, data) => {
      console.log("스토어 cctv 뮤테이션 동작");
      // state.cctvs = cctvs;
      state.cctvs.push(data);
    },
  },

  actions: {
    getTotalCount: ({ commit }) => {
      gugunList(
        ({ response }) => {
          // console.log(data);
          commit("SET_TOTAL_COUNT", response.data.response.body.totalCount);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          // console.log(data);
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = {
        sido: sidoCode,
      };
      gugunList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getDong: ({ commit }, gugunCode) => {
      const params = {
        gugun: gugunCode,
      };
      dongList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getHouseList: ({ commit }, dongCode) => {
      // vue cli enviroment variables 검색
      //.env.local file 생성.
      // 반드시 VUE_APP으로 시작해야 한다.
      const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;

      const params = {
        LAWD_CD: dongCode[1],
        EubmyundongCode: dongCode[2],
        DEAL_YMD: dongCode[0], //`houseStore.state.DEAL_YMD`
        numOfRows: houseStore.state.totalCount,
        pageNo: houseStore.state.pageNo,
        serviceKey: decodeURIComponent(SERVICE_KEY),
      };
      houseList(
        params,
        (response) => {
          commit("SET_HOUSES_LIST", response.data.response.body.items.item);
          // console.log(
          //   "set houses list" + response.data.response.body.items.item,
          // );
          // commit("SET_HOUSE_LIST", response.data.response.body.items.item);
          // console.log(
          //   "set house list" + response.data.response.body.items.item,
          // );
          commit("SET_TOTAL_COUNT", response.data.response.body.totalCount);
          // console.log(response.data.response.body.totalCount);
          // console.log("param으로 받은 값 " + yearmonth);
          // console.log("구군코드 받은 값 " + gugunCode);
          // console.log(houseStore.state.totalCount);
          // console.log("페이지번호" + houseStore.state.pageNo);
          // console.log(this.$state.totalCount);
          console.log(houseStore.state.totalCount);
          console.log(houseStore.state.pageNo);
          console.log(dongCode[1]);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getCctvList: ({ commit }) => {
      // vue cli enviroment variables 검색
      //.env.local file 생성.
      // 반드시 VUE_APP으로 시작해야 한다.

      const params = {
        numOfRows: 100,
      };
      cctvList(
        params,
        async (response) => {
          console.log("response 성공");
          var xml = response.data;
          var json = convert.xml2json(xml, { compact: true });
          // console.log("set cctv list" + json);
          // console.log(json.data[latitude]);
          // console.log("cctvs[] : " + houseStore.state.cctvs);
          var cctvdata = JSON.parse(json);
          console.log(response.data);
          console.log(json);
          console.log(cctvdata.response.body.items.item);
          await commit("SET_CCTV_LIST", cctvdata.response.body.items.item);
          // console.log(JSON.stringify(cctvdata.response.body.items.item));
          console.log(cctvdata.response.body.items.item[0].latitude._text);
          console.log(cctvdata.response.body.items.item[0].longitude._text);
          console.log(JSON.stringify(houseStore.state.cctvs));
          console.log(houseStore.state.cctvs);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
    // getCctvList: ({ commit }) => {
    //   console.log("store에서 출력좀해라");
    //   // vue cli enviroment variables 검색
    //   //.env.local file 생성.
    //   // 반드시 VUE_APP으로 시작해야 한다.
    //   // const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;

    //   // const params = {
    //   //   serviceKey:
    //   //     "w19bwIsYtK36Ch5eN%2FkjkzkaakUTie8kNHGfIeSlxEXyaREGj8tR6xAl7zxK8mja59l8U2Z8zQ%2BFHiLu4HH52A%3D%3D",
    //   // };
    //   axios
    //     .get(
    //       "http://api.data.go.kr/openapi/tn_pubr_public_cctv_api?serviceKey=w19bwIsYtK36Ch5eN%2FkjkzkaakUTie8kNHGfIeSlxEXyaREGj8tR6xAl7zxK8mja59l8U2Z8zQ%2BFHiLu4HH52A%3D%3D",
    //       {
    //         params: {
    //           pageNo: 0,
    //           numOfRows: 100,
    //         },
    //         headers: { "Content-type": "application/json" },
    //       },
    //     )
    //     .then(function (response) {
    //       commit("SET_CCTV_LIST", response.data);
    //       console.log(response.data);
    //     })
    //     .catch(function (error) {
    //       console.log(error);
    //     });
    //   // houseList(
    //   //   params,
    //   //   (response) => {
    //   //     commit("SET_CCTV_LIST", response.data);
    //   //     commit("SET_TOTAL_COUNT", response.data.response.body.totalCount);
    //   //   },
    //   //   (error) => {
    //   //     console.log(error);
    //   //   },
    //   // );
    // },
  },
};

export default houseStore;
