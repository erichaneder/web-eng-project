<template>
  <div class="container mx-auto my-12 px-4 mb-[220px]">
    <div class="flex flex-wrap -mx-4">
      <div class="w-full md:w-1/2 px-4 mb-6 md:mb-0">
        <img :src="product.image" :alt="product.name" class="w-full h-full object-cover rounded" />
      </div>
      <div class="w-full md:w-1/2 px-4">
        <NormalHeading :text="product.name" />
        <p class="text-gray-600 mb-1">SKU: {{ product.sku }}</p>
        <p class="text-pink-800 font-bold mb-2">{{ product.price }}</p>
        <p class="text-gray-500 mb-3">inkl. MwSt., zzgl. Versandkosten</p>

        <div class="flex items-center mb-4 space-x-2">
          <label class="uppercase text-gray-700 font-bold" for="sizeSelect">Größe</label>
            <select class="block appearance-none w-40 border border-gray-200 text-gray-700 py-3 px-4 rounded" id="sizeSelect">
              <option v-for="size in product.sizes" :key="size">{{ size }}</option>
            </select>
        </div>

        <div class="flex items-center mb-4 space-x-2">
          <label for="quantityInput" class="font-bold text-gray-700 uppercase">Menge  &nbsp;</label>
          <input type="number" class="form-control block w-40 border border-gray-200 text-gray-700 py-3 px-4 rounded" id="quantityInput" v-model="quantity" min="1">
        </div>

        <button @click="addToCart" class="btn btn-primary w-full bg-teal-700 text-white p-2 rounded hover:bg-teal-500 mb-2">In den Warenkorb</button>

        <p class="text-gray-500 mt-3">Lieferzeit: 3-5 Werktage</p>
        <hr class="my-4">
        <p class="text-gray-600">Bitte beachten Sie das sich die Lieferzeit aufgrund ihres Standorts ändern kann. Bei Fragen zu diesem oder anderen Produkten können Sie sich an unseren Support wenden.</p>
      </div>
    </div>
  </div>
</template>

<script>
import NormalHeading from "@/components/atoms/NormalHeading.vue";

export default {
  data() {
    return {
      product: {
        name: "Nike Dunk Low Sneakers - White",
        image: "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/d01ef37b-c14a-4edd-8787-534f5732294c/dunk-low-retro-herrenschuh-dd36JB.png",
        sku: "309030",
        price: "€109,99",
        sizes: ["XS", "S", "M", "L", "XL"]
      },
      selectedSize: "M",
      quantity: 1
    };
  },
  mounted() {
    // Fetch product data using the product ID
    //const productId = this.id;
    //this.product = this.$store.getters.getProductById(productId); // With Vuex Store or with other data source
  },
  methods: {
    addToCart() {
      //logic to add the product to the cart
      this.$router.push({ path: '/login' });
    }
  },
  components: {
    NormalHeading
  },
};
</script>