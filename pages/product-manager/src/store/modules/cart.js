// test data





const seller1 = {
  id: 0,
  name: 'seller1',
  productsList: [
    {
      id: 2,
      imageSrc: 'https://i.biz-gid.com/img/products/800/216796.png',
      name: 'Хліб',
      priceForMeasurementUnit: 8.50,
      quantity: 2,
      measurementUnit: 'шт.',
    },
    {
      id: 3,
      imageSrc: 'https://img2.zakaz.ua/src.1470729330.ad72436478c_2016-08-09_Aleksey/src.1470729330.SNCPSG10.obj.0.1.jpg.oe.jpg.pf.jpg.1350nowm.jpg.1350x.jpg',
      name: 'Моршинська (сильногазована)',
      priceForMeasurementUnit: 9999.50,
      quantity: 1,
      measurementUnit: 'л.',
    }
  ]
};


const seller2 = {
  id: 1,
  name: 'seller2',
  productsList: [
    {
      id: 0,
      sellerId: 0,
      name: 'Апельсин',
      imageSrc: 'https://wp.miray.com.ua/site/miray/files/wallpapers/wallpaper-4187.jpg',
      priceForMeasurementUnit: 15.60,
      sellerName: 'marius961',
      measurementUnit: 'кг.',
      quantity: 3,
    },
    {
      id: 1,
      sellerId: 1,
      imageSrc: 'https://irecommend.ru/sites/default/files/product-images/42233/zwr0obbJCnszZYssy91zA.jpg',
      name: 'Мука',
      priceForMeasurementUnit: 20,
      quantity: 2,
      measurementUnit: 'кг.',
    }
  ]
};

//

export default  {
  state: {
    cartAndSellers: []
  },
  actions: {
    fetchCart(context) {
      //request to server...
      context.commit('setCartItems', [seller1, seller2])
    },
    deleteItemFromCartById(context, itemId) {
      //request to server...
      context.commit('removeCartItem', itemId)
    },
    updateItemQuantity(context, itemData) {
      //request to server...
      context.commit('setItemQuantity', itemData);
      alert('changed')
    }
  },
  mutations: {
    setCartItems(state, cartItems) {
      state.cartAndSellers = cartItems;
    },
    removeCartItem(state, id) {
      state.cartAndSellers.map((seller, sellerIndex) => {
        let index = seller.productsList.findIndex(obj => obj.id === id);
        if (index > -1) {
          seller.productsList.splice(index, 1);
          if (seller.productsList <= 0) {
            state.cartAndSellers.splice(sellerIndex, 1);
          }
          return false;
        }
      });
    },
    setItemQuantity(state, itemData) {
      let sellerIndex = state.cartAndSellers.findIndex(obj => obj.id === itemData.sellerId);
      let productIndex = state.cartAndSellers[sellerIndex].productsList.findIndex(obj => obj.id === itemData.itemId);
      state.cartAndSellers[sellerIndex].productsList[productIndex].quantity = itemData.quantity
    }
  },
  getters: {
    getAllCartItems(state) {
      const allProducts = [];
      state.cartAndSellers.forEach((seller) => {
        allProducts.push(...seller.productsList);
      });
      return allProducts;
    },
    getSellersWithProducts(state) {
      return state.cartAndSellers;
    }
  }
}
