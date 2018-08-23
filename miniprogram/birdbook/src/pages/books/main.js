import Vue from 'vue'
import Book from './books'

Vue.config.productionTip = false

const app = new Vue(Book)
app.$mount()
