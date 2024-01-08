#address.js中设置checkout页的addressid多余

Owner: linlinjava

Repo: litemall

Labels: 

## xiaoLogin (20 Jul 2018)

checkout中onShow事件已经判断如果storage中有addressId就设置进data里了，所以这里的代码是多余的。
`      prevPage.setData({
        addressId: event.currentTarget.dataset.addressId
      })`

## linlinjava (20 Jul 2018)

fix 129cd27f1fe358326e16ed68fbc0a62b5b3219c7

