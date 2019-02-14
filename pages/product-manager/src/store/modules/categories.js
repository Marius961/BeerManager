const category1 = {
  id: 0,
  name: 'Пекарня',
  subcategories: [
    {
      id: 0,
      name: 'Хліб',
    },
    {
      id: 1,
      name: 'Хлібці',
    }
  ]
};
const category2 = {
  id: 2,
  name: 'Бакалія',
  img: '',
  subcategories: [
    {
      id: 0,
      name: 'Макарони',
    },
    {
      id: 1,
      name: 'Замороженные хлебобулочные изделия',
    }
  ]
};

export default {
  state: {
    categoriesMap: []
  },
  actions: {
    fetchCategories(context) {
      context.commit('setCategoriesMap', [category2, category1])
    }
  },
  mutations: {
    setCategoriesMap(state, payload) {
      state.categoriesMap = payload;
    },
  },
  getters: {
    getCategoriesMap(state) {
      return state.categoriesMap
    }
  }
}
