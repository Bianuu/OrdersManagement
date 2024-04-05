package DataAccessDAO;

import connection.ConnectionFactory;

import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static connection.ConnectionFactory.getConnection;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * Insereaza obiectul intr-o baza de date
     *
     * @param obj - obiectul curent
     */
    public int inserare(T obj) {
        int idValoare = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = interogareInserare();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int index = 1;
            for (Field field : type.getDeclaredFields()) {
                if (field.getName().equals("id"))
                    continue;
                field.setAccessible(true);
                Object valoare = field.get(obj);
                String valueType = valoare.getClass().getSimpleName();
                switch (valueType) {
                    case "String" -> statement.setString(index, valoare.toString());
                    case "Double" -> statement.setDouble(index, Double.parseDouble(valoare.toString()));
                    case "Integer" -> statement.setInt(index, Integer.parseInt(valoare.toString()));
                }
                index++;
            }
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                idValoare = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return idValoare;
    }

    /**
     * Creaza o comanda SQL pentru a insera in baza de date
     *
     * @return - comanda SQL pentru a insera in baza de date
     */
    private String interogareInserare() {
        StringBuilder stringBuilder = new StringBuilder("INSERT INTO `" + type.getSimpleName() + "` (");

        for (Field field : type.getDeclaredFields()) {
            if (field.getName().equals("id"))
                continue;
            field.setAccessible(true);
            stringBuilder.append(field.getName()).append(", ");
        }
        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append(") VALUES (");

        for (Field field : type.getDeclaredFields()) {
            if (field.getName().equals("id"))
                continue;
            field.setAccessible(true);
            stringBuilder.append("?, ");
        }
        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    /**
     * Updateaza obiectul curent
     */
    public void update(T obj) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String interogare = interogareUpdate();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(interogare);
            int index = 1;
            Object idValoare = -1;
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getName().equals("id")) {
                    idValoare = field.get(obj);
                    continue;
                }

                Object value = field.get(obj);
                String valueType = value.getClass().getSimpleName();

                switch (valueType) {
                    case "String" -> statement.setString(index, value.toString());
                    case "Double" -> statement.setDouble(index, Double.parseDouble(value.toString()));
                    case "Integer" -> statement.setInt(index, Integer.parseInt(value.toString()));
                }
                index++;
            }
            statement.setInt(index, Integer.parseInt(idValoare.toString()));
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Creaza o comanda SQL pentru a updata obiectul curent din baza de date
     */
    private String interogareUpdate() {
        StringBuilder stringBuilder = new StringBuilder("UPDATE `" + type.getSimpleName() + "` SET ");

        for (Field field : type.getDeclaredFields()) {
            if (field.getName().equals("id"))
                continue;
            field.setAccessible(true);
            stringBuilder.append(field.getName()).append(" = ?, ");
        }
        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append(" WHERE id = ?");

        return stringBuilder.toString();
    }

    /**
     * Sterge obiectul curent din baza de date
     */
    public void sterge(T obj) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String interogare = interogareStergere();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(interogare);
            Field idField = type.getDeclaredFields()[0];
            idField.setAccessible(true);
            int idDeSters = Integer.parseInt(idField.get(obj).toString());
            statement.setInt(1, idDeSters);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:deleteClient " + e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Creaza comanda SQL pentru a sterge obiectul curent din baza de date
     */
    private String interogareStergere() {
        return "DELETE FROM `" +
                type.getSimpleName() +
                "` WHERE id = ?";
    }

    /**
     * Cauta obiectul curent dupa id
     *
     * @param id - obiectului
     */
    public T cautaDupaId(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = creareInterogareSelect();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Creaza un SQL Query pentru a citi date din baza de date
     */
    private String creareInterogareSelect() {
        return "SELECT " +
                " * " +
                " FROM " +
                type.getSimpleName() +
                " WHERE id = ?";
    }

    /**
     * Creaza obiecte pe baza datelor citite din baza de date
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Initializeaza tabelul curent cu datele din baza de date
     */
    public DefaultTableModel initializareTabel() {
        String[] columnNames = new String[type.getDeclaredFields().length];
        int indexx = 0;
        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            columnNames[indexx] = field.getName();
            indexx++;
        }

        List<T> tableEntryList = cautaTot();
        Object[][] data = new Object[tableEntryList.size()][indexx];

        int index = 0;
        for (T t : tableEntryList) {
            ArrayList<Object> rowData = new ArrayList<>();
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(t);
                    rowData.add(value);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            data[index] = rowData.toArray();
            index++;
        }
        return new DefaultTableModel(data, columnNames);
    }

    /**
     * Cauta toate obiectele in baza de date
     */
    public List<T> cautaTot() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = interogareSelecteazaTot();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Creaza comanda SQL pentru a interoga baza de date
     */
    private String interogareSelecteazaTot() {
        return "SELECT * FROM `" +
                type.getSimpleName() +
                "`";
    }
}
