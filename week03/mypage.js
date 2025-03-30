const fixBtn = document.getElementById("fixBtn");
const editButtons = document.getElementById("editButtons");
const applyBtn = document.getElementById("applyBtn");
const cancelBtn = document.getElementById("cancelBtn");
const inputs = document.querySelectorAll("input[type='text']");
const radios = document.querySelectorAll("input[type='radio']");
const selects = document.querySelectorAll("select");

let originalValues = [];
let originalRadios = [];
let originalSelects = [];
let isEditing = false;

// 페이지 로딩 시: input은 readOnly, select는 disabled, radio는 그대로 (활성화 상태 유지)
window.addEventListener("DOMContentLoaded", () => {
  inputs.forEach(input => input.readOnly = true);
  selects.forEach(select => {
    select.disabled = true;
    select.classList.add("readonly-select");
  });

  // 라디오 버튼 이벤트 걸기 (선택 막기용)
  radios.forEach((radio, i) => {
    radio.dataset.index = i; // 인덱스를 저장해둠
    radio.addEventListener('change', (e) => {
      if (!isEditing) {
        e.preventDefault();
        e.stopImmediatePropagation();
        // 선택 못하게 원래 상태로 복원
        radio.checked = originalRadios[radio.dataset.index];
      }
    });
  });
});

// Fix 버튼 클릭 시
fixBtn.addEventListener("click", () => {
  isEditing = true;

  originalValues = Array.from(inputs).map(input => input.value);
  originalRadios = Array.from(radios).map(radio => radio.checked);
  originalSelects = Array.from(selects).map(select => select.value);

  inputs.forEach(input => input.readOnly = false);
  selects.forEach(select => {
    select.disabled = false;
    select.classList.remove("readonly-select");
  });

  fixBtn.classList.add("hidden");
  editButtons.classList.remove("hidden");
});

// 적용 버튼 클릭 시
applyBtn.addEventListener("click", () => {
    const phoneInput = Array.from(inputs).find(input =>
        input.value.replace(/[^0-9]/g, "").length === 11
      );
      
      if (!phoneInput || !/^010\d{8}$/.test(phoneInput.value)) {
        alert("전화번호는 반드시 '010'으로 시작하고 총 11자리여야 합니다.");
        return;
      }

  isEditing = false;

  inputs.forEach(input => input.readOnly = true);
  selects.forEach(select => {
    select.disabled = true;
    select.classList.add("readonly-select");
  });

  editButtons.classList.add("hidden");
  fixBtn.classList.remove("hidden");

  alert("수정이 적용되었습니다!");
});

// 취소 버튼 클릭 시
cancelBtn.addEventListener("click", () => {
  isEditing = false;

  inputs.forEach((input, i) => {
    input.value = originalValues[i];
    input.readOnly = true;
  });

  radios.forEach((radio, i) => {
    radio.checked = originalRadios[i];
  });

  selects.forEach((select, i) => {
    select.value = originalSelects[i];
    select.disabled = true;
    select.classList.add("readonly-select");
  });

  editButtons.classList.add("hidden");
  fixBtn.classList.remove("hidden");
});
