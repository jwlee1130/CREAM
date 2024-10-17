const bellIcon = document.querySelector('.bell-icon');
const tooltip = document.querySelector('.tooltip');

bellIcon.addEventListener('click', () => {
  tooltip.classList.toggle('show');
});

document.addEventListener('click', (event) => {
  if (!tooltip.contains(event.target) && !bellIcon.contains(event.target)) {
    tooltip.classList.remove('show');
  }
});

document.addEventListener('DOMContentLoaded', () => {
  const swiper = new Swiper('.main-swiper-container', {
    slidesPerView: 1,
    loop: true, // 무한 루프
    autoplay: {
      delay: 15000,
      disableOnInteraction: false,
    },
    // If you want pagination
    pagination: {
      el: '.swiper-pagination',
      clickable: true,
    },
    // Navigation arrows
    navigation: {
      nextEl: '.swiper-button-next',
      prevEl: '.swiper-but'
          + 'ton-prev',
    },
    // 슬라이드 전환 효과
    effect: 'slide', // 'fade', 'cube', 'coverflow', 'flip' 등 선택 가능
    spaceBetween:  0,
    centeredSlides: true,
    // 반응형 설정 등 추가 가능
  });

  const brandSwiper = new Swiper('.swiper-container', {
    loop: true,
    slidesPerView: 3, // 한 번에 보여줄 슬라이드 수
    slidesPerGroup: 1, // 슬라이드 이동 시 그룹 단위
    spaceBetween: 30, // 슬라이드 간 간격
    autoplay: {
      delay: 10000,
      disableOnInteraction: false,
    },
    navigation: {
      nextEl: '.swiper-button-next',
      prevEl: '.swiper-button-prev',
    }
  });

});
