import Api from '../util/Api';

const apiPrefixUri = 'api';

// http get method request
export function getMethod(apiUrl, params, config) {
  config = config || {};
  config.params = params;
  return Api.get(`${apiPrefixUri}/${apiUrl}`, config);
}

// http post method request
export function postMethod(apiUrl, body, config) {
  body = body || {};
  return Api.post(`${apiPrefixUri}/${apiUrl}`, body, config);
}

// http put method request
export function putMethod(apiUrl, body, config) {
  body = body || {};
  return Api.put(`${apiPrefixUri}/${apiUrl}`, body, config);
}

// http patch method request
export function patchMethod(apiUrl, body, config) {
  body = body || {};
  return Api.patch(`${apiPrefixUri}/${apiUrl}`, body, config);
}

// http delete method request
export function deleteMethod(apiUrl, config) {
  return Api.delete(`${apiPrefixUri}/${apiUrl}`, config);
}
