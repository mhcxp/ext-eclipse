package galaxy.sqlanlysis.core.contant;

import galaxy.sqlanlysis.core.type.AnyType;
import galaxy.sqlanlysis.core.type.BigDecimalType;
import galaxy.sqlanlysis.core.type.BigIntegerType;
import galaxy.sqlanlysis.core.type.BinaryType;
import galaxy.sqlanlysis.core.type.BlobType;
import galaxy.sqlanlysis.core.type.BooleanType;
import galaxy.sqlanlysis.core.type.ByteType;
import galaxy.sqlanlysis.core.type.CalendarDateType;
import galaxy.sqlanlysis.core.type.CalendarType;
import galaxy.sqlanlysis.core.type.CharArrayType;
import galaxy.sqlanlysis.core.type.CharacterArrayType;
import galaxy.sqlanlysis.core.type.CharacterType;
import galaxy.sqlanlysis.core.type.ClassType;
import galaxy.sqlanlysis.core.type.ClobType;
import galaxy.sqlanlysis.core.type.CurrencyType;
import galaxy.sqlanlysis.core.type.DateType;
import galaxy.sqlanlysis.core.type.DoubleType;
import galaxy.sqlanlysis.core.type.FloatType;
import galaxy.sqlanlysis.core.type.IntegerType;
import galaxy.sqlanlysis.core.type.LocaleType;
import galaxy.sqlanlysis.core.type.LongType;
import galaxy.sqlanlysis.core.type.NullableType;
import galaxy.sqlanlysis.core.type.SerializableType;
import galaxy.sqlanlysis.core.type.ShortType;
import galaxy.sqlanlysis.core.type.StringType;
import galaxy.sqlanlysis.core.type.TextType;
import galaxy.sqlanlysis.core.type.TimeType;
import galaxy.sqlanlysis.core.type.TimeZoneType;
import galaxy.sqlanlysis.core.type.TimestampType;
import galaxy.sqlanlysis.core.type.TrueFalseType;
import galaxy.sqlanlysis.core.type.Type;
import galaxy.sqlanlysis.core.type.WrapperBinaryType;
import galaxy.sqlanlysis.core.type.YesNoType;

import java.io.Serializable;

public interface DataTypeConstant {
	/**
	 * <tt>long</tt> type.
	 */
	public static final NullableType LONG = new LongType();
	/**
	 * <tt>short</tt> type.
	 */
	public static final NullableType SHORT = new ShortType();
	/**
	 * <tt>integer</tt> type.
	 */
	public static final NullableType INTEGER = new IntegerType();
	/**
	 * <tt>byte</tt> type.
	 */
	public static final NullableType BYTE = new ByteType();
	/**
	 * <tt>float</tt> type.
	 */
	public static final NullableType FLOAT = new FloatType();
	/**
	 * <tt>double</tt> type.
	 */
	public static final NullableType DOUBLE = new DoubleType();
	/**
	 * <tt>character</tt> type.
	 */
	public static final NullableType CHARACTER = new CharacterType();
	/**
	 * <tt>string</tt> type.
	 */
	public static final NullableType STRING = new StringType();
	/**
	 * <tt>time</tt> type.
	 */
	public static final NullableType TIME = new TimeType();
	/**
	 * <tt>date</tt> type.
	 */
	public static final NullableType DATE = new DateType();
	/**
	 * <tt>timestamp</tt> type.
	 */
	public static final NullableType TIMESTAMP = new TimestampType();
	/**
	 * <tt>boolean</tt> type.
	 */
	public static final NullableType BOOLEAN = new BooleanType();
	/**
	 * <tt>true_false</tt> type.
	 */
	public static final NullableType TRUE_FALSE = new TrueFalseType();
	/**
	 * <tt>yes_no</tt> type.
	 */
	public static final NullableType YES_NO = new YesNoType();
	/**
	 * <tt>big_decimal</tt> type.
	 */
	public static final NullableType BIG_DECIMAL = new BigDecimalType();
	/**
	 * <tt>big_integer</tt> type.
	 */
	public static final NullableType BIG_INTEGER = new BigIntegerType();
	/**
	 * <tt>binary</tt> type.
	 */
	public static final NullableType BINARY = new BinaryType();
	/**
	 * <tt>wrapper-binary</tt> type.
	 */
	public static final NullableType WRAPPER_BINARY = new WrapperBinaryType();
	/**
	 * char[] type.
	 */
	public static final NullableType CHAR_ARRAY = new CharArrayType();
	/**
	 * Character[] type.
	 */
	public static final NullableType CHARACTER_ARRAY = new CharacterArrayType();
	/**
	 * <tt>text</tt> type.
	 */
	public static final NullableType TEXT = new TextType();
	/**
	 * <tt>blob</tt> type.
	 */
	public static final Type BLOB = new BlobType();
	/**
	 * <tt>clob</tt> type.
	 */
	public static final Type CLOB = new ClobType();
	/**
	 * <tt>calendar</tt> type.
	 */
	public static final NullableType CALENDAR = new CalendarType();
	/**
	 * <tt>calendar_date</tt> type.
	 */
	public static final NullableType CALENDAR_DATE = new CalendarDateType();
	/**
	 * <tt>locale</tt> type.
	 */
	public static final NullableType LOCALE = new LocaleType();
	/**
	 * <tt>currency</tt> type.
	 */
	public static final NullableType CURRENCY = new CurrencyType();
	/**
	 * <tt>timezone</tt> type.
	 */
	public static final NullableType TIMEZONE = new TimeZoneType();
	/**
	 * <tt>class</tt> type.
	 */
	public static final NullableType CLASS = new ClassType();
	/**
	 * <tt>serializable</tt> type.
	 */
	public static final NullableType SERIALIZABLE = new SerializableType(
			Serializable.class);
	/**
	 * <tt>object</tt> type.
	 */
	public static final Type OBJECT = new AnyType();
}
