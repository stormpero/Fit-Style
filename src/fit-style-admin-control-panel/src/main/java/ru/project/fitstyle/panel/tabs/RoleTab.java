/*package ru.project.fitstyle.panel.tabs;

import ru.project.fitstyle.exception.UnauthorizedException;
import ru.project.fitstyle.panel.CustomJPanel;

public class RoleTab extends CustomJPanel {

    private final DefaultTableModel model;

    public TestTab() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[][]{}, new String[] {"Идентификатор", "Тренировака"});
        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JScrollPane scrollPane = new JScrollPane(table);
        // Force the scrollbars to always be displayed
        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane);
    }

    @Override
    public void update() throws UnauthorizedException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilder();
        ConnectionService connectionService = ConnectionService.getInstance();
        HttpURLConnection httpURLConnection = connectionBuilder.prepareRequestWithAuthHeader(Url.ALL_ROLES.GET);
        httpURLConnection = connectionBuilder.prepareRequest(httpURLConnection, ConnectionType.GET);
        String response = null;
        try {
            response = connectionService.sendGet(httpURLConnection);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            AllRolesResponse allRolesResponse = new ObjectMapper().readValue(response, AllRolesResponse.class);
            updatePanel(allRolesResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void updatePanel(AllRolesResponse allRolesResponse) {
        //Clear all data
        model.setRowCount(0);

        for(AllRolesResponse.RoleDto roleDto : allRolesResponse.getRoles()) {
            model.addRow(new Object[]{roleDto.getId(), roleDto.getName()});
        }
    }
}
*/