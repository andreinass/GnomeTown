package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.app.gnometown.Model.Gnome;
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

public class GnomeRealmProxy extends Gnome
    implements RealmObjectProxy, GnomeRealmProxyInterface {

    static final class GnomeColumnInfo extends ColumnInfo {

        public final long idIndex;
        public final long nameIndex;
        public final long thumbnailIndex;
        public final long ageIndex;
        public final long weightIndex;
        public final long heightIndex;
        public final long hair_colorIndex;
        public final long professionsIndex;
        public final long friendsIndex;

        GnomeColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(9);
            this.idIndex = getValidColumnIndex(path, table, "Gnome", "id");
            indicesMap.put("id", this.idIndex);

            this.nameIndex = getValidColumnIndex(path, table, "Gnome", "name");
            indicesMap.put("name", this.nameIndex);

            this.thumbnailIndex = getValidColumnIndex(path, table, "Gnome", "thumbnail");
            indicesMap.put("thumbnail", this.thumbnailIndex);

            this.ageIndex = getValidColumnIndex(path, table, "Gnome", "age");
            indicesMap.put("age", this.ageIndex);

            this.weightIndex = getValidColumnIndex(path, table, "Gnome", "weight");
            indicesMap.put("weight", this.weightIndex);

            this.heightIndex = getValidColumnIndex(path, table, "Gnome", "height");
            indicesMap.put("height", this.heightIndex);

            this.hair_colorIndex = getValidColumnIndex(path, table, "Gnome", "hair_color");
            indicesMap.put("hair_color", this.hair_colorIndex);

            this.professionsIndex = getValidColumnIndex(path, table, "Gnome", "professions");
            indicesMap.put("professions", this.professionsIndex);

            this.friendsIndex = getValidColumnIndex(path, table, "Gnome", "friends");
            indicesMap.put("friends", this.friendsIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final GnomeColumnInfo columnInfo;
    private RealmList<RealmString> professionsRealmList;
    private RealmList<RealmString> friendsRealmList;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("name");
        fieldNames.add("thumbnail");
        fieldNames.add("age");
        fieldNames.add("weight");
        fieldNames.add("height");
        fieldNames.add("hair_color");
        fieldNames.add("professions");
        fieldNames.add("friends");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    GnomeRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (GnomeColumnInfo) columnInfo;
    }

    @SuppressWarnings("cast")
    public int realmGet$id() {
        ((RealmObject) this).realm.checkIfValid();
        return (int) ((RealmObject) this).row.getLong(columnInfo.idIndex);
    }

    public void realmSet$id(int value) {
        ((RealmObject) this).realm.checkIfValid();
        ((RealmObject) this).row.setLong(columnInfo.idIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$name() {
        ((RealmObject) this).realm.checkIfValid();
        return (java.lang.String) ((RealmObject) this).row.getString(columnInfo.nameIndex);
    }

    public void realmSet$name(String value) {
        ((RealmObject) this).realm.checkIfValid();
        if (value == null) {
            ((RealmObject) this).row.setNull(columnInfo.nameIndex);
            return;
        }
        ((RealmObject) this).row.setString(columnInfo.nameIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$thumbnail() {
        ((RealmObject) this).realm.checkIfValid();
        return (java.lang.String) ((RealmObject) this).row.getString(columnInfo.thumbnailIndex);
    }

    public void realmSet$thumbnail(String value) {
        ((RealmObject) this).realm.checkIfValid();
        if (value == null) {
            ((RealmObject) this).row.setNull(columnInfo.thumbnailIndex);
            return;
        }
        ((RealmObject) this).row.setString(columnInfo.thumbnailIndex, value);
    }

    @SuppressWarnings("cast")
    public int realmGet$age() {
        ((RealmObject) this).realm.checkIfValid();
        return (int) ((RealmObject) this).row.getLong(columnInfo.ageIndex);
    }

    public void realmSet$age(int value) {
        ((RealmObject) this).realm.checkIfValid();
        ((RealmObject) this).row.setLong(columnInfo.ageIndex, value);
    }

    @SuppressWarnings("cast")
    public float realmGet$weight() {
        ((RealmObject) this).realm.checkIfValid();
        return (float) ((RealmObject) this).row.getFloat(columnInfo.weightIndex);
    }

    public void realmSet$weight(float value) {
        ((RealmObject) this).realm.checkIfValid();
        ((RealmObject) this).row.setFloat(columnInfo.weightIndex, value);
    }

    @SuppressWarnings("cast")
    public float realmGet$height() {
        ((RealmObject) this).realm.checkIfValid();
        return (float) ((RealmObject) this).row.getFloat(columnInfo.heightIndex);
    }

    public void realmSet$height(float value) {
        ((RealmObject) this).realm.checkIfValid();
        ((RealmObject) this).row.setFloat(columnInfo.heightIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$hair_color() {
        ((RealmObject) this).realm.checkIfValid();
        return (java.lang.String) ((RealmObject) this).row.getString(columnInfo.hair_colorIndex);
    }

    public void realmSet$hair_color(String value) {
        ((RealmObject) this).realm.checkIfValid();
        if (value == null) {
            ((RealmObject) this).row.setNull(columnInfo.hair_colorIndex);
            return;
        }
        ((RealmObject) this).row.setString(columnInfo.hair_colorIndex, value);
    }

    public RealmList<RealmString> realmGet$professions() {
        ((RealmObject) this).realm.checkIfValid();
        // use the cached value if available
        if (professionsRealmList != null) {
            return professionsRealmList;
        } else {
            LinkView linkView = ((RealmObject) this).row.getLinkList(columnInfo.professionsIndex);
            professionsRealmList = new RealmList<RealmString>(RealmString.class, linkView, realm);
            return professionsRealmList;
        }
    }

    public void realmSet$professions(RealmList<RealmString> value) {
        ((RealmObject) this).realm.checkIfValid();
        LinkView links = ((RealmObject) this).row.getLinkList(columnInfo.professionsIndex);
        links.clear();
        if (value == null) {
            return;
        }
        for (RealmObject linkedObject : (RealmList<? extends RealmObject>) value) {
            if (!linkedObject.isValid()) {
                throw new IllegalArgumentException("Each element of 'value' must be a valid managed object.");
            }
            if (linkedObject.realm != this.realm) {
                throw new IllegalArgumentException("Each element of 'value' must belong to the same Realm.");
            }
            links.add(((RealmObject) linkedObject).row.getIndex());
        }
    }

    public RealmList<RealmString> realmGet$friends() {
        ((RealmObject) this).realm.checkIfValid();
        // use the cached value if available
        if (friendsRealmList != null) {
            return friendsRealmList;
        } else {
            LinkView linkView = ((RealmObject) this).row.getLinkList(columnInfo.friendsIndex);
            friendsRealmList = new RealmList<RealmString>(RealmString.class, linkView, realm);
            return friendsRealmList;
        }
    }

    public void realmSet$friends(RealmList<RealmString> value) {
        ((RealmObject) this).realm.checkIfValid();
        LinkView links = ((RealmObject) this).row.getLinkList(columnInfo.friendsIndex);
        links.clear();
        if (value == null) {
            return;
        }
        for (RealmObject linkedObject : (RealmList<? extends RealmObject>) value) {
            if (!linkedObject.isValid()) {
                throw new IllegalArgumentException("Each element of 'value' must be a valid managed object.");
            }
            if (linkedObject.realm != this.realm) {
                throw new IllegalArgumentException("Each element of 'value' must belong to the same Realm.");
            }
            links.add(((RealmObject) linkedObject).row.getIndex());
        }
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_Gnome")) {
            Table table = transaction.getTable("class_Gnome");
            table.addColumn(RealmFieldType.INTEGER, "id", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "name", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "thumbnail", Table.NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "age", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.FLOAT, "weight", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.FLOAT, "height", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "hair_color", Table.NULLABLE);
            if (!transaction.hasTable("class_RealmString")) {
                RealmStringRealmProxy.initTable(transaction);
            }
            table.addColumnLink(RealmFieldType.LIST, "professions", transaction.getTable("class_RealmString"));
            if (!transaction.hasTable("class_RealmString")) {
                RealmStringRealmProxy.initTable(transaction);
            }
            table.addColumnLink(RealmFieldType.LIST, "friends", transaction.getTable("class_RealmString"));
            table.addSearchIndex(table.getColumnIndex("id"));
            table.setPrimaryKey("id");
            return table;
        }
        return transaction.getTable("class_Gnome");
    }

    public static GnomeColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_Gnome")) {
            Table table = transaction.getTable("class_Gnome");
            if (table.getColumnCount() != 9) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 9 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 9; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final GnomeColumnInfo columnInfo = new GnomeColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("id")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'id' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("id") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'int' for field 'id' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.idIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'id' does support null values in the existing Realm file. Use corresponding boxed type for field 'id' or migrate using io.realm.internal.Table.convertColumnToNotNullable().");
            }
            if (table.getPrimaryKey() != table.getColumnIndex("id")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Primary key not defined for field 'id' in existing Realm file. Add @PrimaryKey.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("id"))) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Index not defined for field 'id' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("name")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'name' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("name") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'name' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.nameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'name' is required. Either set @Required to field 'name' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("thumbnail")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'thumbnail' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("thumbnail") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'thumbnail' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.thumbnailIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'thumbnail' is required. Either set @Required to field 'thumbnail' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("age")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'age' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("age") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'int' for field 'age' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.ageIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'age' does support null values in the existing Realm file. Use corresponding boxed type for field 'age' or migrate using io.realm.internal.Table.convertColumnToNotNullable().");
            }
            if (!columnTypes.containsKey("weight")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'weight' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("weight") != RealmFieldType.FLOAT) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'float' for field 'weight' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.weightIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'weight' does support null values in the existing Realm file. Use corresponding boxed type for field 'weight' or migrate using io.realm.internal.Table.convertColumnToNotNullable().");
            }
            if (!columnTypes.containsKey("height")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'height' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("height") != RealmFieldType.FLOAT) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'float' for field 'height' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.heightIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'height' does support null values in the existing Realm file. Use corresponding boxed type for field 'height' or migrate using io.realm.internal.Table.convertColumnToNotNullable().");
            }
            if (!columnTypes.containsKey("hair_color")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'hair_color' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("hair_color") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'hair_color' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.hair_colorIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'hair_color' is required. Either set @Required to field 'hair_color' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("professions")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'professions'");
            }
            if (columnTypes.get("professions") != RealmFieldType.LIST) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'RealmString' for field 'professions'");
            }
            if (!transaction.hasTable("class_RealmString")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing class 'class_RealmString' for field 'professions'");
            }
            Table table_7 = transaction.getTable("class_RealmString");
            if (!table.getLinkTarget(columnInfo.professionsIndex).hasSameSchema(table_7)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid RealmList type for field 'professions': '" + table.getLinkTarget(columnInfo.professionsIndex).getName() + "' expected - was '" + table_7.getName() + "'");
            }
            if (!columnTypes.containsKey("friends")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'friends'");
            }
            if (columnTypes.get("friends") != RealmFieldType.LIST) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'RealmString' for field 'friends'");
            }
            if (!transaction.hasTable("class_RealmString")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing class 'class_RealmString' for field 'friends'");
            }
            Table table_8 = transaction.getTable("class_RealmString");
            if (!table.getLinkTarget(columnInfo.friendsIndex).hasSameSchema(table_8)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid RealmList type for field 'friends': '" + table.getLinkTarget(columnInfo.friendsIndex).getName() + "' expected - was '" + table_8.getName() + "'");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The Gnome class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Gnome";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static Gnome createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        Gnome obj = null;
        if (update) {
            Table table = realm.getTable(Gnome.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (!json.isNull("id")) {
                long rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("id"));
                if (rowIndex != TableOrView.NO_MATCH) {
                    obj = new GnomeRealmProxy(realm.schema.getColumnInfo(Gnome.class));
                    ((RealmObject) obj).realm = realm;
                    ((RealmObject) obj).row = table.getUncheckedRow(rowIndex);
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (GnomeRealmProxy) realm.createObject(Gnome.class, null);
                } else {
                    obj = (GnomeRealmProxy) realm.createObject(Gnome.class, json.getInt("id"));
                }
            } else {
                obj = (GnomeRealmProxy) realm.createObject(Gnome.class);
            }
        }
        if (json.has("id")) {
            if (json.isNull("id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field id to null.");
            } else {
                ((GnomeRealmProxyInterface) obj).realmSet$id((int) json.getInt("id"));
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                ((GnomeRealmProxyInterface) obj).realmSet$name(null);
            } else {
                ((GnomeRealmProxyInterface) obj).realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("thumbnail")) {
            if (json.isNull("thumbnail")) {
                ((GnomeRealmProxyInterface) obj).realmSet$thumbnail(null);
            } else {
                ((GnomeRealmProxyInterface) obj).realmSet$thumbnail((String) json.getString("thumbnail"));
            }
        }
        if (json.has("age")) {
            if (json.isNull("age")) {
                throw new IllegalArgumentException("Trying to set non-nullable field age to null.");
            } else {
                ((GnomeRealmProxyInterface) obj).realmSet$age((int) json.getInt("age"));
            }
        }
        if (json.has("weight")) {
            if (json.isNull("weight")) {
                throw new IllegalArgumentException("Trying to set non-nullable field weight to null.");
            } else {
                ((GnomeRealmProxyInterface) obj).realmSet$weight((float) json.getDouble("weight"));
            }
        }
        if (json.has("height")) {
            if (json.isNull("height")) {
                throw new IllegalArgumentException("Trying to set non-nullable field height to null.");
            } else {
                ((GnomeRealmProxyInterface) obj).realmSet$height((float) json.getDouble("height"));
            }
        }
        if (json.has("hair_color")) {
            if (json.isNull("hair_color")) {
                ((GnomeRealmProxyInterface) obj).realmSet$hair_color(null);
            } else {
                ((GnomeRealmProxyInterface) obj).realmSet$hair_color((String) json.getString("hair_color"));
            }
        }
        if (json.has("professions")) {
            if (json.isNull("professions")) {
                ((GnomeRealmProxyInterface) obj).realmSet$professions(null);
            } else {
                ((GnomeRealmProxyInterface) obj).realmGet$professions().clear();
                JSONArray array = json.getJSONArray("professions");
                for (int i = 0; i < array.length(); i++) {
                    com.app.gnometown.Model.RealmString item = RealmStringRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((GnomeRealmProxyInterface) obj).realmGet$professions().add(item);
                }
            }
        }
        if (json.has("friends")) {
            if (json.isNull("friends")) {
                ((GnomeRealmProxyInterface) obj).realmSet$friends(null);
            } else {
                ((GnomeRealmProxyInterface) obj).realmGet$friends().clear();
                JSONArray array = json.getJSONArray("friends");
                for (int i = 0; i < array.length(); i++) {
                    com.app.gnometown.Model.RealmString item = RealmStringRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((GnomeRealmProxyInterface) obj).realmGet$friends().add(item);
                }
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static Gnome createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        Gnome obj = realm.createObject(Gnome.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field id to null.");
                } else {
                    ((GnomeRealmProxyInterface) obj).realmSet$id((int) reader.nextInt());
                }
            } else if (name.equals("name")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GnomeRealmProxyInterface) obj).realmSet$name(null);
                } else {
                    ((GnomeRealmProxyInterface) obj).realmSet$name((String) reader.nextString());
                }
            } else if (name.equals("thumbnail")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GnomeRealmProxyInterface) obj).realmSet$thumbnail(null);
                } else {
                    ((GnomeRealmProxyInterface) obj).realmSet$thumbnail((String) reader.nextString());
                }
            } else if (name.equals("age")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field age to null.");
                } else {
                    ((GnomeRealmProxyInterface) obj).realmSet$age((int) reader.nextInt());
                }
            } else if (name.equals("weight")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field weight to null.");
                } else {
                    ((GnomeRealmProxyInterface) obj).realmSet$weight((float) reader.nextDouble());
                }
            } else if (name.equals("height")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field height to null.");
                } else {
                    ((GnomeRealmProxyInterface) obj).realmSet$height((float) reader.nextDouble());
                }
            } else if (name.equals("hair_color")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GnomeRealmProxyInterface) obj).realmSet$hair_color(null);
                } else {
                    ((GnomeRealmProxyInterface) obj).realmSet$hair_color((String) reader.nextString());
                }
            } else if (name.equals("professions")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GnomeRealmProxyInterface) obj).realmSet$professions(null);
                } else {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.app.gnometown.Model.RealmString item = RealmStringRealmProxy.createUsingJsonStream(realm, reader);
                        ((GnomeRealmProxyInterface) obj).realmGet$professions().add(item);
                    }
                    reader.endArray();
                }
            } else if (name.equals("friends")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((GnomeRealmProxyInterface) obj).realmSet$friends(null);
                } else {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.app.gnometown.Model.RealmString item = RealmStringRealmProxy.createUsingJsonStream(realm, reader);
                        ((GnomeRealmProxyInterface) obj).realmGet$friends().add(item);
                    }
                    reader.endArray();
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static Gnome copyOrUpdate(Realm realm, Gnome object, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        if (((RealmObject) object).realm != null && ((RealmObject) object).realm.threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (((RealmObject) object).realm != null && ((RealmObject) object).realm.getPath().equals(realm.getPath())) {
            return object;
        }
        Gnome realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(Gnome.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = table.findFirstLong(pkColumnIndex, ((GnomeRealmProxyInterface) object).realmGet$id());
            if (rowIndex != TableOrView.NO_MATCH) {
                realmObject = new GnomeRealmProxy(realm.schema.getColumnInfo(Gnome.class));
                ((RealmObject) realmObject).realm = realm;
                ((RealmObject) realmObject).row = table.getUncheckedRow(rowIndex);
                cache.put(object, (RealmObjectProxy) realmObject);
            } else {
                canUpdate = false;
            }
        }

        if (canUpdate) {
            return update(realm, realmObject, object, cache);
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static Gnome copy(Realm realm, Gnome newObject, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        Gnome realmObject = realm.createObject(Gnome.class, ((GnomeRealmProxyInterface) newObject).realmGet$id());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ((GnomeRealmProxyInterface) realmObject).realmSet$id(((GnomeRealmProxyInterface) newObject).realmGet$id());
        ((GnomeRealmProxyInterface) realmObject).realmSet$name(((GnomeRealmProxyInterface) newObject).realmGet$name());
        ((GnomeRealmProxyInterface) realmObject).realmSet$thumbnail(((GnomeRealmProxyInterface) newObject).realmGet$thumbnail());
        ((GnomeRealmProxyInterface) realmObject).realmSet$age(((GnomeRealmProxyInterface) newObject).realmGet$age());
        ((GnomeRealmProxyInterface) realmObject).realmSet$weight(((GnomeRealmProxyInterface) newObject).realmGet$weight());
        ((GnomeRealmProxyInterface) realmObject).realmSet$height(((GnomeRealmProxyInterface) newObject).realmGet$height());
        ((GnomeRealmProxyInterface) realmObject).realmSet$hair_color(((GnomeRealmProxyInterface) newObject).realmGet$hair_color());

        RealmList<RealmString> professionsList = ((GnomeRealmProxyInterface) newObject).realmGet$professions();
        if (professionsList != null) {
            RealmList<RealmString> professionsRealmList = ((GnomeRealmProxyInterface) realmObject).realmGet$professions();
            for (int i = 0; i < professionsList.size(); i++) {
                RealmString professionsItem = professionsList.get(i);
                RealmString cacheprofessions = (RealmString) cache.get(professionsItem);
                if (cacheprofessions != null) {
                    professionsRealmList.add(cacheprofessions);
                } else {
                    professionsRealmList.add(RealmStringRealmProxy.copyOrUpdate(realm, professionsList.get(i), update, cache));
                }
            }
        }


        RealmList<RealmString> friendsList = ((GnomeRealmProxyInterface) newObject).realmGet$friends();
        if (friendsList != null) {
            RealmList<RealmString> friendsRealmList = ((GnomeRealmProxyInterface) realmObject).realmGet$friends();
            for (int i = 0; i < friendsList.size(); i++) {
                RealmString friendsItem = friendsList.get(i);
                RealmString cachefriends = (RealmString) cache.get(friendsItem);
                if (cachefriends != null) {
                    friendsRealmList.add(cachefriends);
                } else {
                    friendsRealmList.add(RealmStringRealmProxy.copyOrUpdate(realm, friendsList.get(i), update, cache));
                }
            }
        }

        return realmObject;
    }

    public static Gnome createDetachedCopy(Gnome realmObject, int currentDepth, int maxDepth, Map<RealmObject, CacheData<RealmObject>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmObject> cachedObject = cache.get(realmObject);
        Gnome standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (Gnome)cachedObject.object;
            } else {
                standaloneObject = (Gnome)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new Gnome();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmObject>(currentDepth, standaloneObject));
        }
        ((GnomeRealmProxyInterface) standaloneObject).realmSet$id(((GnomeRealmProxyInterface) realmObject).realmGet$id());
        ((GnomeRealmProxyInterface) standaloneObject).realmSet$name(((GnomeRealmProxyInterface) realmObject).realmGet$name());
        ((GnomeRealmProxyInterface) standaloneObject).realmSet$thumbnail(((GnomeRealmProxyInterface) realmObject).realmGet$thumbnail());
        ((GnomeRealmProxyInterface) standaloneObject).realmSet$age(((GnomeRealmProxyInterface) realmObject).realmGet$age());
        ((GnomeRealmProxyInterface) standaloneObject).realmSet$weight(((GnomeRealmProxyInterface) realmObject).realmGet$weight());
        ((GnomeRealmProxyInterface) standaloneObject).realmSet$height(((GnomeRealmProxyInterface) realmObject).realmGet$height());
        ((GnomeRealmProxyInterface) standaloneObject).realmSet$hair_color(((GnomeRealmProxyInterface) realmObject).realmGet$hair_color());

        // Deep copy of professions
        if (currentDepth == maxDepth) {
            ((GnomeRealmProxyInterface) standaloneObject).realmSet$professions(null);
        } else {
            RealmList<RealmString> managedprofessionsList = ((GnomeRealmProxyInterface) realmObject).realmGet$professions();
            RealmList<RealmString> standaloneprofessionsList = new RealmList<RealmString>();
            ((GnomeRealmProxyInterface) standaloneObject).realmSet$professions(standaloneprofessionsList);
            int nextDepth = currentDepth + 1;
            int size = managedprofessionsList.size();
            for (int i = 0; i < size; i++) {
                RealmString item = RealmStringRealmProxy.createDetachedCopy(managedprofessionsList.get(i), nextDepth, maxDepth, cache);
                standaloneprofessionsList.add(item);
            }
        }

        // Deep copy of friends
        if (currentDepth == maxDepth) {
            ((GnomeRealmProxyInterface) standaloneObject).realmSet$friends(null);
        } else {
            RealmList<RealmString> managedfriendsList = ((GnomeRealmProxyInterface) realmObject).realmGet$friends();
            RealmList<RealmString> standalonefriendsList = new RealmList<RealmString>();
            ((GnomeRealmProxyInterface) standaloneObject).realmSet$friends(standalonefriendsList);
            int nextDepth = currentDepth + 1;
            int size = managedfriendsList.size();
            for (int i = 0; i < size; i++) {
                RealmString item = RealmStringRealmProxy.createDetachedCopy(managedfriendsList.get(i), nextDepth, maxDepth, cache);
                standalonefriendsList.add(item);
            }
        }
        return standaloneObject;
    }

    static Gnome update(Realm realm, Gnome realmObject, Gnome newObject, Map<RealmObject, RealmObjectProxy> cache) {
        ((GnomeRealmProxyInterface) realmObject).realmSet$name(((GnomeRealmProxyInterface) newObject).realmGet$name());
        ((GnomeRealmProxyInterface) realmObject).realmSet$thumbnail(((GnomeRealmProxyInterface) newObject).realmGet$thumbnail());
        ((GnomeRealmProxyInterface) realmObject).realmSet$age(((GnomeRealmProxyInterface) newObject).realmGet$age());
        ((GnomeRealmProxyInterface) realmObject).realmSet$weight(((GnomeRealmProxyInterface) newObject).realmGet$weight());
        ((GnomeRealmProxyInterface) realmObject).realmSet$height(((GnomeRealmProxyInterface) newObject).realmGet$height());
        ((GnomeRealmProxyInterface) realmObject).realmSet$hair_color(((GnomeRealmProxyInterface) newObject).realmGet$hair_color());
        RealmList<RealmString> professionsList = ((GnomeRealmProxyInterface) newObject).realmGet$professions();
        RealmList<RealmString> professionsRealmList = ((GnomeRealmProxyInterface) realmObject).realmGet$professions();
        professionsRealmList.clear();
        if (professionsList != null) {
            for (int i = 0; i < professionsList.size(); i++) {
                RealmString professionsItem = professionsList.get(i);
                RealmString cacheprofessions = (RealmString) cache.get(professionsItem);
                if (cacheprofessions != null) {
                    professionsRealmList.add(cacheprofessions);
                } else {
                    professionsRealmList.add(RealmStringRealmProxy.copyOrUpdate(realm, professionsList.get(i), true, cache));
                }
            }
        }
        RealmList<RealmString> friendsList = ((GnomeRealmProxyInterface) newObject).realmGet$friends();
        RealmList<RealmString> friendsRealmList = ((GnomeRealmProxyInterface) realmObject).realmGet$friends();
        friendsRealmList.clear();
        if (friendsList != null) {
            for (int i = 0; i < friendsList.size(); i++) {
                RealmString friendsItem = friendsList.get(i);
                RealmString cachefriends = (RealmString) cache.get(friendsItem);
                if (cachefriends != null) {
                    friendsRealmList.add(cachefriends);
                } else {
                    friendsRealmList.add(RealmStringRealmProxy.copyOrUpdate(realm, friendsList.get(i), true, cache));
                }
            }
        }
        return realmObject;
    }

    @Override
    public String toString() {
        if (!isValid()) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Gnome = [");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{thumbnail:");
        stringBuilder.append(realmGet$thumbnail() != null ? realmGet$thumbnail() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{age:");
        stringBuilder.append(realmGet$age());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{weight:");
        stringBuilder.append(realmGet$weight());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{height:");
        stringBuilder.append(realmGet$height());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{hair_color:");
        stringBuilder.append(realmGet$hair_color() != null ? realmGet$hair_color() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{professions:");
        stringBuilder.append("RealmList<RealmString>[").append(realmGet$professions().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{friends:");
        stringBuilder.append("RealmList<RealmString>[").append(realmGet$friends().size()).append("]");
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
        GnomeRealmProxy aGnome = (GnomeRealmProxy)o;

        String path = ((RealmObject) this).realm.getPath();
        String otherPath = ((RealmObject) aGnome).realm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = ((RealmObject) this).row.getTable().getName();
        String otherTableName = ((RealmObject) aGnome).row.getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (((RealmObject) this).row.getIndex() != ((RealmObject) aGnome).row.getIndex()) return false;

        return true;
    }

}
