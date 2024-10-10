const bellIcon = document.querySelector('.bell-icon');
const tooltip = document.querySelector('.tooltip');

bellIcon.addEventListener('click', () => {
  // tooltip의 show 클래스를 토글하여 팝업을 나타내거나 숨김
  tooltip.classList.toggle('show');
});

document.addEventListener('click', (event) => {
  if (!tooltip.contains(event.target) && !bellIcon.contains(event.target)) {
    tooltip.classList.remove('show'); // show 클래스 제거하여 툴팁 숨기기
  }
});

document.addEventListener('DOMContentLoaded', () => {
  const swiper = new Swiper('.main-swiper-container', {
    // Optional parameters
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
      prevEl: '.swiper-button-prev',
    },
    // 슬라이드 전환 효과
    effect: 'slide', // 'fade', 'cube', 'coverflow', 'flip' 등 선택 가능
    // 기타 설정
    spaceBetween: 30,
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
