<template>
  <div v-if="product" class="container mx-auto my-12 px-4 mb-[220px]">
    <div class="flex flex-wrap -mx-4">
      <div class="w-full md:w-1/2 px-4 mb-6 md:mb-0">
        <img :src="product.image" :alt="product.name" class="w-full h-full object-cover rounded" />
      </div>
      <div class="w-full md:w-1/2 px-4">
        <NormalHeading :text="product.name" />
        <p class="text-gray-600 mb-1">SKU: {{ product.sku }}</p>
        <p class="text-pink-800 font-bold mb-2">€ {{ product.price }}</p>
        <p class="text-gray-500 mb-3">inkl. MwSt., zzgl. Versandkosten</p>

        <div class="flex items-center mb-4 space-x-2">
          <label class="uppercase text-gray-700 font-bold" for="sizeSelect">Size</label>
            <select class="block appearance-none w-40 border border-gray-200 text-gray-700 py-3 px-4 rounded" id="sizeSelect">
              <option v-for="size in product.sizes" :key="size">{{ size }}</option>
            </select>
        </div>

        <div class="flex items-center mb-4 space-x-2">
          <label for="quantityInput" class="font-bold text-gray-700 uppercase">Quantity </label>
          <input type="number" class="form-control block w-28 border border-gray-200 text-gray-700 py-3 px-4 rounded" id="quantityInput" v-model="quantity" min="1">
        </div>

        <CustomButton @clicked="addToCart" customButtonStyle="btn btn-primary w-full bg-teal-700 text-white p-2 rounded hover:bg-teal-500 mb-2">Add To Basket</CustomButton>
        <p class="text-gray-500 mt-3">Delivery time: 3-5 working days</p>
        <hr class="my-4">
        <p class="text-gray-600">Please note that the delivery time may vary depending on your location. If you have any questions about this or other products, please contact our support team.</p>
      </div>
    </div>
  </div>
  <div v-else class="container mx-auto my-12 px-4 mb-[220px]">
    <p>Loading product details...</p>
  </div>

   <!-- Recommended Products Section -->
   <div v-if="recommendedProducts.length" class="container mx-auto px-4">
      <NormalHeading text="You might also like" />
      <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
        <ProductTile
          v-for="recommendedProduct in recommendedProducts"
          :key="recommendedProduct.id"
          :product="recommendedProduct"
          @goToProductDetail="fetchProduct"
        />
      </div>
    </div>
</template>

<script>
import CustomButton from '@/components/atoms/Button.vue';  
import ProductTile from '@/components/molecules/ProductTile.vue'; 
import NormalHeading from "@/components/atoms/NormalHeading.vue";
import { useCompleteStore } from "@/store/store";

export default {
  data() {
    return {
      product: null,
      selectedSize: "M",
      quantity: 1,
      store: useCompleteStore(),
      recommendedProducts: [],
    };
  },
  mounted() {
    this.fetchProduct();
  },
  methods: {
    addToCart() {
      //add product with right quantity and size
      const productToAdd = {
        ...this.product,
        quantity: this.quantity,
        selectedSize: this.selectedSize
      };
      this.store.addToBasket(productToAdd);
    },
    fetchProduct(id) {
      var productId;
      if(id) {
        productId = id;
      } else {
        productId = parseInt(this.$route.params.id);
      }
      const product = this.store.getProductById(productId);
      console.log("Product: " + JSON.stringify(product));
      if (product) {
        this.product = {
        ...product, //-> ... = Spread Operator so kann man das object aufspreaden in individuelle Elemente, hier sagt man halt nimm alle Properties von product, und nacher wird das image dann umgsetzt und es werden noch extras hinzugefügt
        sku: 309030,
        sizes: ["XS", "S", "M", "L", "XL"]
      };
      } else {
        console.error("Product not found, with productId: " + productId);
        //Wenn das Product nicht gefunden werden konnte, dann einfach default product anzeigen.
        this.product = {
          name: "Nike Dunk Low Sneakers - White",
          image: "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/d01ef37b-c14a-4edd-8787-534f5732294c/dunk-low-retro-herrenschuh-dd36JB.png",
          sku: "309030",
          price: "€109,99",
          sizes: ["XS", "S", "M", "L", "XL"]
        };
      }
      this.fetchRecommendedProducts(productId);
    },
    fetchRecommendedProducts(productId) {
      // This is a placeholder for your logic to fetch or determine related products
      // For now, let's just pick a few random products from the store
      const allProducts = this.store.getAllProducts;
      this.recommendedProducts = allProducts.filter(p => p.id !== productId).slice(0, 3); // Exclude the current product and limit to 3
    },
  },
  components: {
    NormalHeading,
    CustomButton,
    ProductTile
  },
};
</script>