import axios from 'axios';
import Config from '../config/Config';
import { getToken } from './Auth';

const Api = axios.create({
  baseURL: `${process.env.REACT_APP_LOCAL_API_URL}/`,
  timeout: Config.apiTimeout,
  headers: { 'Content-Type': 'application/json' }
});

Api.defaults.timeout = Config.apiTimeout;
Api.defaults.headers.post['Content-Type'] = 'application/json';

// 요청 인터셉터
Api.interceptors.request.use(
  function (config) {
    console.log('Api.interceptors.request SUCCESS');

    let token = getToken();
    if (config.url && !config.url.includes('api/login') && !config.url.includes('api/signup')) {
      config.headers[`Authorization`] = `Bearer ${token}`;
    } else {
      config.headers[`Authorization`] = ``;
    }
    console.log(config);
    return config;
  },
  function (error) {
    console.log('Api.interceptors.request ERROR');
      console.log(error);

    return Promise.reject(error);
  }
);

// 응답 인터셉터
Api.interceptors.response.use(
  function (response) {
    console.log('Api.interceptors.response SUCCESS');
    return response;
  },
  function (error) {
    console.log('Api.interceptors.response ERROR');
    console.log(error);

    // let errorResponse = error.response || {};
    // let status = errorResponse.status;
    // let requestConfig = error.config;

    // if (error && error.response) {
    //   if (status === 400) {
    //     console.log('Bad Request: ', error.message);
    //     // 입력 폼에 오류 메시지를 표시하거나, 팝업 창으로 알림 등의 처리
    //   } else if (status === 401) {
    //     console.log('Unauthorized: ', error.message);
    //     // 로그인 페이지로 이동하거나, 로그인 팝업을 표시하는 등의 처리
    //   } else if (status === 404) {
    //     console.log('Not Found: ', error.message);
    //     // 404 오류 페이지로 이동하거나, 오류 메시지를 출력하는 등의 처리
    //   } else if (status === 406) {
    //     console.log('Not Acceptable: ', error.message);
    //     // 사용자에게 지원되는 형식을 제공하는 방법 등을 제시하는 등의 처리
    //   } else if (status === 503) {
    //     console.log('Service Unavailable: ', error.message);
    //     // 서비스 이용이 불가능하다는 메시지를 표시하거나, 일시적으로 다시 시도하도록 안내하는 등의 처리
    //   } else {
    //     console.log('Unexpected Error: ', error.message);
    //     // 일반적인 오류 메시지를 출력하는 등의 처리
    //   }
    // } else {
    //   console.log('Api Response Success OR HTTP Option Method Error');
    // }

    return error.response;
  }
);

export default Api;
