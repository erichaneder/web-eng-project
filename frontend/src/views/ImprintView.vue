<template>
  <div class="p-4 min-h-screen bg-gray-100 flex flex-col items-center">
      <NormalHeading text="Imprint" />
      <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-3xl">
          <SubHeading text="Company Information:" />
          <p>Company Name: SneakerShop Inc.</p>
          <p>Address: 123 Shoe Street, Sneaker City, SC 12345</p>
          <p>Telephone: +1 (123) 456-7890</p>
          <p>Email: info@sneakershop.com</p>
          
          <SubHeading text="Legal Information:" />
          <p>Registered Office: Sneaker City</p>
          <p>CEO: John Doe</p>
          <p>Company Registration Number: 12345678</p>
          
          <SubHeading text="Regulatory Authority:" />
          <p>Trade Authority of Sneaker City</p>
          
          <SubHeading text="VAT Identifiaction Number:" />
          <p>SC1234567</p>

          <SubHeading text="Online Dispute Resolution:" />
          <p>In accordance with the European Online Dispute Resolution (ODR) regulation, we inform you about the Online Dispute Resolution Platform (ODR platform). You can reach the ODR platform under the following link: <a href="http://ec.europa.eu/consumers/odr/" target="_blank">http://ec.europa.eu/consumers/odr/</a></p>

          <SubHeading text="Disclaimer:" />
          <p>Despite careful content control, we assume no liability for the content of external links. The content of the linked pages are the sole responsibility of their operators.</p>
      </div>
  </div>
</template>

<script>
import NormalHeading from "@/components/atoms/NormalHeading.vue";
import SubHeading from "@/components/atoms/SubHeading.vue";

export default {
  name: 'ImprintView',
  components: {
    NormalHeading,
    SubHeading
  }
}
</script>
