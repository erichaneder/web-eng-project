<template>
  <div v-if="product" class="container mx-auto my-12 px-4 mb-[220px]">
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

        <CustomButton @click="addToCart" customButtonStyle="btn btn-primary w-full bg-teal-700 text-white p-2 rounded hover:bg-teal-500 mb-2">In den Warenkorb</CustomButton>
        <p class="text-gray-500 mt-3">Lieferzeit: 3-5 Werktage</p>
        <hr class="my-4">
        <p class="text-gray-600">Bitte beachten Sie das sich die Lieferzeit aufgrund ihres Standorts ändern kann. Bei Fragen zu diesem oder anderen Produkten können Sie sich an unseren Support wenden.</p>
      </div>
    </div>
  </div>
  <div v-else class="container mx-auto my-12 px-4 mb-[220px]">
    <p>Loading product details...</p>
  </div>
</template>

<script>
import CustomButton from '@/components/atoms/Button.vue';  
import NormalHeading from "@/components/atoms/NormalHeading.vue";

/*
{
        name: "Nike Dunk Low Sneakers - White",
        image: "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/d01ef37b-c14a-4edd-8787-534f5732294c/dunk-low-retro-herrenschuh-dd36JB.png",
        sku: "309030",
        price: "€109,99",
        sizes: ["XS", "S", "M", "L", "XL"]

*/

export default {
  data() {
    return {
      product: null,
      selectedSize: "M",
      quantity: 1
    };
  },
  mounted() {
    this.fetchProduct();
  },
  methods: {
    addToCart() {
      if(localStorage.getItem("userId") != null) {
        //logic to add the product to the cart
        this.$store.dispatch('addToBasket', {
          id: this.product.id,
          productName: this.product.name,
          productDescription: this.product.description,
          price: this.product.price,
          image: this.product.image
        });
        this.$router.push({ path: '/basket' });
      } else {
        this.$router.push({ path: '/login' });
      }
    },
    fetchProduct() {
      const productId = parseInt(this.$route.params.id);
      const product = this.$store.getters.getProductById(productId);
      if (product) {
        this.product = {
        ...product, //-> ... = Spread Operator so kann man das object aufspreaden in individuelle Elemente, hier sagt man halt nimm alle Properties von product, und nacher wird das image dann umgsetzt und es werden noch extras hinzugefügt
        image: require(`@/assets/${product.image}`),
        sku: 309030,
        sizes: ["XS", "S", "M", "L", "XL"],
        price: product.price + ",00 €"
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
    }
  },
  components: {
    NormalHeading,
    CustomButton
  },
};
</script>