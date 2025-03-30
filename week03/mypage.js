// 요소 선택
const fixBtn = document.getElementById("fixBtn");
const editButtons = document.getElementById("editButtons");
const applyBtn = document.getElementById("applyBtn");
const cancelBtn = document.getElementById("cancelBtn");
const inputs = document.querySelectorAll("input[type='text']");

let originalValues = []; // 이전 값 저장용
let isEditing = false;

// 1. 페이지 로드 시 모든 input 비활성화
window.addEventListener("DOMContentLoaded", () => {
  inputs.forEach(input => {
    input.readOnly = true;
  });
});

// 2. 수정 버튼 클릭 → 입력 활성화 + 버튼 토글
fixBtn.addEventListener("click", () => {
  isEditing = true;
  originalValues = Array.from(inputs).map(input => input.value);
  inputs.forEach(input => input.readOnly = false);

  fixBtn.classList.add("hidden");
  editButtons.classList.remove("hidden");
});

// 3. 적용 버튼 클릭 → 입력값 저장 + 다시 비활성화
applyBtn.addEventListener("click", () => {
  isEditing = false;
  inputs.forEach(input => input.readOnly = true);

  editButtons.classList.add("hidden");
  fixBtn.classList.remove("hidden");

  alert("수정이 적용되었습니다!");
});

// 4. 취소 버튼 클릭 → 원래 값 복원 + 다시 비활성화
cancelBtn.addEventListener("click", () => {
  isEditing = false;
  inputs.forEach((input, i) => {
    input.value = originalValues[i];
    input.readOnly = true;
  });

  editButtons.classList.add("hidden");
  fixBtn.classList.remove("hidden");
});
