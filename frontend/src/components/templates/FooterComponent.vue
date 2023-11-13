<template>
  <footer class="bg-teal-900 text-white mt-8">
    <div class="container mx-auto px-4 py-8">
      <div class="flex flex-wrap justify-around">
        
        <!-- Footer Navigation -->
        <div class="mb-6">
          <h3 class="uppercase font-bold mb-2.5">Quick Links</h3>
          <ul class="list-none footer-links">
            <li class="mb-2">
              <a href="/help" class="hover:text-teal-300">Help</a>
            </li>
            <li class="mb-2">
              <a href="/imprint" class="hover:text-teal-300">Imprint</a>
            </li>
            <li class="mb-2">
              <a href="/contact" class="hover:text-teal-300">Contact</a>
            </li>
          </ul>
        </div>

        <!-- Payment Methods -->
        <div class="mb-6">
          <h3 class="uppercase font-bold mb-2.5">Payment Methods</h3>
          <div class="flex items-center space-x-3">
           <img src="@/assets/visa.svg" alt="Visa" class="h-8">
           <img src="@/assets/mastercard.svg" alt="MasterCard" class="h-8">
           <img src="@/assets/paypal.svg" alt="PayPal" class="h-8"> 
           <img src="@/assets/amex.svg" alt="Amex" class="h-8"> 
           <img src="@/assets/apple_pay.svg" alt="Apple Pay" class="h-8"> 
          </div>
        </div>
      </div>

      <!-- Footer Copyright -->
      <div class="w-full md:w-auto text-center">
         <p>&copy; 2023 SneakerShop. All rights reserved.</p>
      </div>

    </div>
  </footer>
</template>

<script>
export default {
    name: 'FooterComponent'
}
</script>
