#Deserialize generic derived class

Owner: alibaba

Repo: fastjson

Labels: 

## jinghui70 (30 Oct 2012)

Code fragment：

```
public abstract class IdObject<I> {
     private I id;
     public I getId() {
        return id;
     }
     public void setId(I id) {
        this.id = id;
     }
}
public class Child extends IdObject<Long> {
    public static void main(String[] args) {
        String str = {"id":0};
        Child child = JSON.parseObject(str, Child.class);
        System.out.println(child.getId().getClass());
    }
}
```

I'm using fast-json 1.1.23. The 'id' property of child should be Long, but was Integer.

I modified com.alibaba.fastjson.util.FieldInfo.java to solve this issue， but I'm not sure if such a change is appropriate：

I insert this lines at line 75:

```
    if (clazz != null && fieldClass == Object.class && fieldType instanceof TypeVariable) {
        TypeVariable<?> tv = (TypeVariable<?>) fieldType;
        Type genericFieldType = getInheritGenericType(clazz, tv);
        if (genericFieldType != null) {
            this.fieldClass = TypeUtils.getClass(genericFieldType);
            this.fieldType = genericFieldType;
            return;
        }
    }
```

and write a function:

```
public static Type getInheritGenericType(Class<?> clazz, TypeVariable<?> tv) {
    Type type = null;
    GenericDeclaration gd = tv.getGenericDeclaration();
    do {
        type = clazz.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType ptype = (ParameterizedType) type;
            if (ptype.getRawType() == gd) {
                TypeVariable<?>[] tvs = gd.getTypeParameters();
                Type[] types = ptype.getActualTypeArguments();
                for (int i = 0; i < tvs.length; i++) {
                    if (tvs[i] == tv)
                        return types[i];
                }
                return null;
            }
        }
        clazz = TypeUtils.getClass(type);
    } while (type != null);
    return null;
}
```


## wenshao (07 Nov 2012)

thank you report this bug, i have been merged into the chunk. 


