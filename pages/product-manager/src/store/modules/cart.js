let carIte1 = {
  id: 0,
  sellerId: 0,
  name: 'Апельсин',
  imageSrc: 'https://wp.miray.com.ua/site/miray/files/wallpapers/wallpaper-4187.jpg',
  priceForMeasurementUnit: 15.60,
  sellerName: 'marius961',
  measurementUnit: 'кг.',
  quantity: 3,
};
let carIte2 = {
  id: 1,
  sellerId: 1,
  imageSrc: 'https://irecommend.ru/sites/default/files/product-images/42233/zwr0obbJCnszZYssy91zA.jpg',
  name: 'Мука',
  priceForMeasurementUnit: 20,
  quantity: 2,
  measurementUnit: 'кг.',
};
let carIte3 = {
  id: 2,
  sellerId: 2,
  imageSrc: 'https://i.biz-gid.com/img/products/800/216796.png',
  name: 'Хліб',
  priceForMeasurementUnit: 8.50,
  quantity: 2,
  measurementUnit: 'шт.',
};
let carIte4 = {
  id: 3,
  sellerId: 3,
  imageSrc: 'https://img2.zakaz.ua/src.1470729330.ad72436478c_2016-08-09_Aleksey/src.1470729330.SNCPSG10.obj.0.1.jpg.oe.jpg.pf.jpg.1350nowm.jpg.1350x.jpg',
  name: 'Моршинська (сильногазована)',
  priceForMeasurementUnit: 12.50,
  quantity: 1,
  measurementUnit: 'л.',
};

export default  {
  state: {
    cart: [],
  },
  actions: {
    fetchCart(context) {
      //request to server...
      context.commit('setCartItems', [carIte1, carIte2, carIte3, carIte4])
    },
    deleteItemFromCartById(context, itemId) {
      //request to server...
      context.commit('removeCartItem', itemId)
    }
  },
  mutations: {
    setCartItems(state, cartItems) {
      state.cart = cartItems;
    },
    removeCartItem(state, id) {
      let index = state.cart.findIndex(obj => obj.id === id);
      state.cart.splice(index, 1);
    }
  },
  getters: {
    getCartItems(state) {
      return state.cart
    }
  }
}
