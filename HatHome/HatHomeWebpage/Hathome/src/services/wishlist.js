import Vue from 'vue'
import axios from 'axios'

class Wishlist {

  addToWishlist (productId, productName, userId) {
    //TODO : get current userId
    axios.post('http://localhost:9005/wishlist', {
      user_id: userId,
      product_id: productId
    })
      .then(
        (response) => {
          alert(`Added ${productName} to wishlist`)
        }
      )
      .catch(
        (error) => {
          console.log('in catch', error);
        }
      )
  }

  deleteFromWishlist (productId, userId) {
    console.log(`DELETE`);
    return axios.delete('http://localhost:9005/wishlist/' + productId + '/user/' + userId)
      .then(
        (response) => {
          alert(`Deleted ${productName} to wishlist`)
        }
      )
      .catch(
        (error) => {
          console.log('in catch', error);
        }
      )
  }
}
export default Wishlist = new Wishlist();
