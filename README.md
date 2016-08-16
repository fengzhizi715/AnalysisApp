# AnalysisApp
一个快速分析某个app使用哪些sdk的小工具。对广告sdk进行了特别的关照,因为一开始想写一个分析使用哪些广告sdk的工具。

大致思路是读取app的AndroidManifest.xml文件，将其转换成相应的对象。然后遍历对象中Activity、Service、meta-data、Receiver等对象列表，将其跟sdk的特征值进行匹配。如果匹配成功表示app包含该sdk，并记录下来。

Handler用于处理sdk的特征值，它大部分使用groovy编写，因为groovy的语法糖可以少写很多代码。

整个项目使用Spring来管理各个Handler，使用责任链（Chain of Responsibility）模式将各个Handler串联起来。

对于某些Handler特别复杂，比如TencentHandler和BaiduHandler，采用DFA的算法进行优化。






