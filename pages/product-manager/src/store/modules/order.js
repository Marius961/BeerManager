const addressList = [
  {
    id: 0,
    recipientFullName: "Іван Васильович",
    recipientMobileNumber: "+381848754948",
    region: "Львівська",
    city: "Львів",
    street: "Степана Бандери",
    buildingNumber: "12 a",
    apartmentNumber: "2",
    zipCode: "78008"
  },
  {
    id: 1,
    recipientFullName: "Олег Васильович",
    recipientMobileNumber: "+381848154948",
    region: "Ки]вська обл. Бориспіль qwd qwdqwdwqd",
    city: "Київ",
    street: "Хрещатик",
    buildingNumber: "28Б",
    apartmentNumber: "46",
    zipCode: "79008"
  },
  {
    id: 3,
    recipientFullName: "Олег Васильович",
    recipientMobileNumber: "+381848154948",
    region: "Ки]вська обл. Бориспіль",
    city: "Київ",
    street: "Хрещатик",
    buildingNumber: "28Б",
    apartmentNumber: "46",
    zipCode: "79008"
  }
]


export default {
  state: {
    sellerId: null,
    orderItems: [],
    addressList: [],
    comment: ""
  },
  actions: {
    fetchAddressList(context) {
      setTimeout(() => {
        context.commit("setAddressList", addressList)
      }, 500)
    },
    postOrder(context, order) {
      console.log(order)
    }
  },
  mutations: {
    setAddressList(state, addressList) {
      state.addressList = addressList;
    }
  },
  getters: {
    getAddressList(state) {
      return state.addressList;
    }
  }
}
