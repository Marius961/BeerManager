// test data

const testCartItemsSellers = [
  {
    id: 0,
    name: 'seller1',
    fullName: "Василь Степанович"
  },
  {
    id: 1,
    name: 'seller2',
    fullName: "Іван Васильович"
  }
];

const testCartItems = [
  {
    id: 1,
    name: 'Мука',
    sellerId: 1,
    image: 'https://irecommend.ru/sites/default/files/product-images/42233/zwr0obbJCnszZYssy91zA.jpg',
    priceForMeasurementUnit: 20,
    measurementUnit: {
      id: 13,
      name: 'кг'
    },
    quantity: 2
  },
  {
    id: 0,
    name: 'Апельсин',
    sellerId: 0,
    image: 'https://wp.miray.com.ua/site/miray/files/wallpapers/wallpaper-4187.jpg',
    priceForMeasurementUnit: 15.60,
    measurementUnit: {
      id: 13,
      name: 'кг'
    },
    quantity: 3
  },
  {
    id: 3,
    name: 'Моршинська (сильногазована)',
    sellerId: 0,
    image: 'https://img2.zakaz.ua/src.1470729330.ad72436478c_2016-08-09_Aleksey/src.1470729330.SNCPSG10.obj.0.1.jpg.oe.jpg.pf.jpg.1350nowm.jpg.1350x.jpg',
    priceForMeasurementUnit: 9999.50,
    measurementUnit: {
      id: 3,
      name: 'л'
    },
    quantity: 1
  },
  {
    id: 2,
    name: 'Хліб',
    sellerId: 1,
    image: 'https://i.biz-gid.com/img/products/800/216796.png',
    priceForMeasurementUnit: 8.50,
    measurementUnit: {
      id: 8,
      name: 'шт'
    },
    quantity: 2
  },


];

//

export default  {
  state: {
    cartItems: [],
    cartSellersList: []
  },
  actions: {
    fetchCartSellersList(context) {
      context.commit('setCartSellers', testCartItemsSellers)
    },
    fetchCart(context) {
      //request to server...
      context.commit('setCartItems', testCartItems)
    },
    deleteItemFromCartById(context, itemId) {
      //request to server...
      context.commit('removeCartItem', itemId)
    },
    updateItemQuantity(context, itemData) {
      //request to server...
      context.commit('setItemQuantity', itemData);
    }
  },
  mutations: {
    setCartItems(state, cartItems) {
      state.cartItems = cartItems;
    },
    setCartSellers(state, sellers) {
      state.cartSellersList = sellers;
    },
    removeCartItem(state, id) {
      const index = state.cartItems.findIndex(obj => obj.id === id);
      state.cartItems.splice(index, 1);
    },

    setItemQuantity(state, itemData) {
      const itemIndex = state.cartItems.findIndex(obj => obj.id === itemData.itemId);
      state.cartItems[itemIndex].quantity = itemData.itemQuantity;
    }
  },
  getters: {
    getAllCartItems(state) {
      return state.cartItems;
    },
    getSellersWithProducts(state) {
      return state.cartSellersList.reduce((sellersAcc, seller, index) => {
        const sellerItems =  state.cartItems.reduce(function(result, cartItem) {
          if (cartItem.sellerId === seller.id) {
            result.push(cartItem)
          }
          return result;
        }, []);
        if (sellerItems.length > 0) {
          sellersAcc.push({...seller, 'cartItems': sellerItems});
        }
        return sellersAcc;
      }, []);
    }
  }
}
