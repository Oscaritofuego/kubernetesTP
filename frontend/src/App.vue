<script>
import axios from 'axios'

let http = axios.create({
  baseURL: '/api',
  headers: {
    'Content-type': 'application/json'
  }
})
export default {
  data() {
    return {
      books: [],
    };
  },
  mounted() {
    http.get(`/books`).then(response => {
      this.books = response.data
      console.log(response.data)
    })
      .catch(e => {
        console.log(e)
      })
  }
}
</script>

<template>
  <main>
    <h4>Liste des livres</h4>
    <ul>
      <li v-for="(book, index) in books"
          :key="index"
      >
        {{ book.title }}
      </li>
    </ul>
  </main>
</template>