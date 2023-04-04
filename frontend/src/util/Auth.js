// token을 로컬 스토리지에 저장하는 함수
export function setToken(token) {
  localStorage.setItem('token', token);
}

// token을 로컬 스토리지에서 가져오는 함수
export function getToken() {
  return localStorage.getItem('token');
}
