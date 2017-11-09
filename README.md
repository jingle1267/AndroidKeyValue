# AndroidKeyValue

Android上基于realm数据库的k-v工具类

### 快速使用

#### 添加依赖

```JSON
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

```JSON
	dependencies {
		compile 'com.ihongqiqu.kv:kv:1.0'
	}
```

#### 1. init

```JAVA
Realm.init(getApplicationContext());
```  

#### 2. insert & update

```JAVA
KeyValueUtil.insertOrUpdate("key1", value);
```

#### 3. delete

```JAVA
KeyValueUtil.delete("key1");
```

#### 4. select

```JAVA
List<KeyValue> list =  KeyValueUtil.query("key1");
```

本组件基于Realm数据库。https://realm.io/docs/java/latest/