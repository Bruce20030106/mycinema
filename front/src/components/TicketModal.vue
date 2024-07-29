<!-- TicketModal.vue -->
<template>
    <div class="modal-overlay">
      <div class="modal-content">
        <h2>确认订单信息</h2>
        <div class="input-item">
          <label for="ticket-count">票数:</label>
          <input id="ticket-count" type="number" v-model="order.ticketCount"/>
        </div>
        <div class="input-item">
          <label for="hall-number">影厅号:</label>
          <select v-model="order.hallNumber">
            <option value="A">A</option>
            <option value="B">B</option>
          </select>
        </div>
        <div class="input-item">
          <label for="seat-number">座位号:</label>
          <select v-model="order.seatNumber">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
          </select>
        </div>
        <div class="input-item">
          <label for="show-time">放映时间:</label>
          <input id="show-time" type="datetime-local" v-model="order.showTime" />
        </div>
        <div id="price-area">
          <span>票价：{{ order.ticketPrice }}￥</span>
        </div>
        <div id="btn-area">
          <button id="confirm-btn" class="model-btn" @click="confirmPurchase">确认购买</button>
          <button id="cancel-btn" class="model-btn" @click="closeModal">取消</button>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { reactive, defineProps, defineEmits, watch } from 'vue';
  import axios from 'axios';
import config from '@/config';

  const props = defineProps({
    modelValue: {
      type: Boolean,
      default: false
    },
    cinemaId: {
      type: Number,
      required: true
    },
    movieId: {
      type: String,
      required: true
    },
    userId: {
      type: Number,
      required: true
    },
    ticketPrice: {
      type: Number,
      required: true
    }
  });
  
  const emit = defineEmits(['update:modelValue']);

  const singleTicketPrice = props.ticketPrice;

  const order = reactive({
    userId: props.userId.toString(),
    movieId: props.movieId.toString(),
    cinemaId: props.cinemaId.toString(),
    hallNumber: '',
    seatNumber: '',
    showTime: '',
    ticketCount: 1,
    ticketPrice: singleTicketPrice
  })

  // 监视ticketCount，负责越界纠正和修改ticketPrice
  watch(() => order.ticketCount, (newVal, oldVal) => {
    if (newVal < 1) {
      order.ticketCount = 1;
    }
    order.ticketPrice = order.ticketCount * singleTicketPrice;
  })
  
  function closeModal() {
    emit('update:modelValue', false);
  }
  
  async function confirmPurchase() {
    if (order.hallNumber == '' || order.seatNumber == '' || order.showTime == '') {
      alert("请填完必要信息！");
      return;
    }
    order.showTime = new Date(order.showTime).toString().slice(0,16); // 将日期转换为符合格式的字符串类型
    order.seatNumber = order.hallNumber + order.seatNumber;
    order.hallNumber = "Hall " + order.hallNumber;
    
  
    try {
      const order_data = {
        ticketCount: order.ticketCount,
        hallNumber: order.hallNumber,
        seatNumber: order.seatNumber,
        showTime: order.showTime,
        userId: order.userId,
        movieId: order.movieId,
        cinemaId: order.cinemaId,
        ticketPrice: order.ticketPrice
      }
      // const order_data_json = JSON.stringify(order_data)
      console.log(order_data)
      const response = await axios.post(`${config.url}orders`, order_data);
      console.log('Order created:', response.data);
      alert("购票成功！")
      closeModal();
    } catch (error) {
      console.error('Error creating order:', error);
    }
  }
  </script>
  

  <style scoped>
  .modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .modal-content {
    background: white;
    padding: 40px;
    border-radius: 5px;
    display: block;
  }

  .input-item {
    width: 250px;
    margin-top: 30px;
    display: flex;
  }

  #price-area {
    margin-top: 30px;
    margin-bottom: 30px;
  }

  .input-item input {
    width: 160px;
    margin-left: auto;
  }

  .input-item select {
    width: 160px;
    margin-left: auto;
  }

  #btn-area {
    display: flex;
    width: 250px;
  }

  .model-btn {
    width: 90px;
    height: 40px;
    border-radius: 8px;
  }

  .model-btn:hover {
    cursor: pointer;
  }

  #confirm-btn {
    background: rgb(1, 173, 231);
    color: white;
    border: none;
  }

  #cancel-btn {
    margin-left: auto;
    background: rgb(212, 18, 18);
    color: white;
    border: none;
  }
  </style>
  