export function getDate (datetime) {
  return new Date(datetime).toLocaleDateString()
}

export function getTime (datetime) {
  return new Date(datetime).toLocaleTimeString()
}

export function formatTime (datetime) {
  return new Date(datetime).toLocaleString()
}

export function generateQueryString (conditionList) {
  let params = new URLSearchParams();
  conditionList.forEach(cond => {
    params.append(cond.category, cond.value);
  })
  return params.toString();
  /*let result = ''
  conditionList.forEach(cond => {
    if (cond.value && cond.value !== '') {
      result += (cond.operator + cond.category + '=' + cond.value + '#')
    }
  })
  return result*/
}

export function highLight (data, pattern) {

  if(typeof(data) === 'string') {
    pattern.forEach(ptn => {
      data = data.split(ptn).join("<span style='color: red'>" + ptn + "</span>")
    })
  }
  return data;
  // data.replace(RegExp(/日本|森林/), "<span style='color: red'>" + pattern + "</span>")
}


export function deepCopy( sourceObj , c) {
  var c = c || ( Array.isArray(sourceObj) ? [ ] : {} );
  for (let i in sourceObj) {
    if (typeof sourceObj[i] === 'object') {
      c[i] = Array.isArray(sourceObj[i])  ? [] : {};
      deepCopy (sourceObj[i], c[i]);
    } else {
      c[i] = sourceObj[i];
    }
  }
  return c;
}
