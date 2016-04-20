package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.app.gnometown.Model.RealmString;
import io.realm.RealmFieldType;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.ImplicitTransaction;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Table;
import io.realm.internal.TableOrView;
import io.realm.internal.android.JsonUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RealmStringRealmProxy extends RealmString
    implements RealmObjectProxy, RealmStringRealmProxyInterface {

    static final class RealmStringColumnInfo extends ColumnInfo {

        public final long stringIndex;

        RealmStringColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(1);
            this.stringIndex = getValidColumnIndex(path, table, "RealmString", "string");
            indicesMap.put("string", this.stringIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final RealmStringColumnInfo columnInfo;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("string");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    RealmStringRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (RealmStringColumnInfo) columnInfo;
    }

    @SuppressWarnings("cast")
    public String realmGet$string() {
        ((RealmObject) this).realm.checkIfValid();
        return (java.lang.String) ((RealmObject) this).row.getString(columnInfo.stringIndex);
    }

    public void realmSet$string(String value) {
        ((RealmObject) this).realm.checkIfValid();
        if (value == null) {
            ((RealmObject) this).row.setNull(columnInfo.stringIndex);
            return;
        }
        ((RealmObject) this).row.setString(columnInfo.stringIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_RealmString")) {
            Table table = transaction.getTable("class_RealmString");
            table.addColumn(RealmFieldType.STRING, "string", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_RealmString");
    }

    public static RealmStringColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_RealmString")) {
            Table table = transaction.getTable("class_RealmString");
            if (table.getColumnCount() != 1) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 1 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 1; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final RealmStringColumnInfo columnInfo = new RealmStringColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("string")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'string' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("string") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'string' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.stringIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'string' is required. Either set @Required to field 'string' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The RealmString class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_RealmString";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static RealmString createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        RealmString obj = realm.createObject(RealmString.class);
        if (json.has("string")) {
            if (json.isNull("string")) {
                ((RealmStringRealmProxyInterface) obj).realmSet$string(null);
            } else {
                ((RealmStringRealmProxyInterface) obj).realmSet$string((String) json.getString("string"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static RealmString createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        RealmString obj = realm.createObject(RealmString.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("string")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((RealmStringRealmProxyInterface) obj).realmSet$string(null);
                } else {
                    ((RealmStringRealmProxyInterface) obj).realmSet$string((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static RealmString copyOrUpdate(Realm realm, RealmString object, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        if (((RealmObject) object).realm != null && ((RealmObject) object).realm.threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (((RealmObject) object).realm != null && ((RealmObject) object).realm.getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static RealmString copy(Realm realm, RealmString newObject, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        RealmString realmObject = realm.createObject(RealmString.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ((RealmStringRealmProxyInterface) realmObject).realmSet$string(((RealmStringRealmProxyInterface) newObject).realmGet$string());
        return realmObject;
    }

    public static RealmString createDetachedCopy(RealmString realmObject, int currentDepth, int maxDepth, Map<RealmObject, CacheData<RealmObject>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmObject> cachedObject = cache.get(realmObject);
        RealmString standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (RealmString)cachedObject.object;
            } else {
                standaloneObject = (RealmString)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new RealmString();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmObject>(currentDepth, standaloneObject));
        }
        ((RealmStringRealmProxyInterface) standaloneObject).realmSet$string(((RealmStringRealmProxyInterface) realmObject).realmGet$string());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!isValid()) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("RealmString = [");
        stringBuilder.append("{string:");
        stringBuilder.append(realmGet$string() != null ? realmGet$string() : "null");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        String realmName = ((RealmObject) this).realm.getPath();
        String tableName = ((RealmObject) this).row.getTable().getName();
        long rowIndex = ((RealmObject) this).row.getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealmStringRealmProxy aRealmString = (RealmStringRealmProxy)o;

        String path = ((RealmObject) this).realm.getPath();
        String otherPath = ((RealmObject) aRealmString).realm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = ((RealmObject) this).row.getTable().getName();
        String otherTableName = ((RealmObject) aRealmString).row.getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (((RealmObject) this).row.getIndex() != ((RealmObject) aRealmString).row.getIndex()) return false;

        return true;
    }

}
