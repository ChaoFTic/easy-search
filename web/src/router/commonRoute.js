export default [{
  path: '/',
  name: '首页',
  component: () => import('@/views/Home')
}, {
  path: 'result',
  name: '结果页',
  component: () => import('@/views/Result')
}, {
  path: 'index',
  name: '索引页',
  component: () => import('@/views/Index')
}]
