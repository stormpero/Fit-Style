package ru.project.fitstyle.json.response;

import java.util.Date;
import java.util.List;

public class AllUsersResponse {
    private List<FitUserFullInfoDto> fitUsers;

    public AllUsersResponse(List<FitUserFullInfoDto> fitUsers) {
        this.fitUsers = fitUsers;
    }

    public AllUsersResponse() {
    }

    public List<FitUserFullInfoDto> getFitUsers() {
        return fitUsers;
    }


    public static class FitUserFullInfoDto {
        private FitUserDto fitUserInfo;

        private SubscriptionDto subscriptionInfo;

        private List<RoleDto> roles;

        public FitUserFullInfoDto(FitUserDto fitUserInfo, SubscriptionDto subscriptionInfo, List<RoleDto> roles) {
            this.fitUserInfo = fitUserInfo;
            this.subscriptionInfo = subscriptionInfo;
            this.roles = roles;
        }

        public FitUserFullInfoDto() {
        }

        public FitUserDto getFitUserInfo() {
            return fitUserInfo;
        }

        public SubscriptionDto getSubscriptionInfo() {
            return subscriptionInfo;
        }

        public List<RoleDto> getRoles() {
            return roles;
        }





        public static class RoleDto {
            private Long id;

            private String name;

            public RoleDto() {
            }

            public RoleDto(final Long id, final String name) {
                this.id = id;
                this.name = name;
            }

            public Long getId() {
                return id;
            }

            public String getName() {
                return name;
            }
        }




        public static class FitUserDto {

            private Long id;

            private String email;

            private String name;

            private String surname;

            private String patronymic;

            private String age;

            private String gender;

            private Date birthdate;

            private String telephone;

            private String passport;

            private String address;

            private String imgURL;

            private Long balance;

            private Boolean enabled;

            public FitUserDto() {
            }

            public FitUserDto(final Long id, final String email, final String name, final String surname, final String patronymic,
                              final String age, final String gender, final Date birthdate, final String telephone, final String passport, final String address,
                              final String imgURL, final Long balance, final Boolean enabled) {
                this.id = id;
                this.email = email;
                this.name = name;
                this.surname = surname;
                this.patronymic = patronymic;
                this.age = age;
                this.gender = gender;
                this.birthdate = birthdate;
                this.telephone = telephone;
                this.passport = passport;
                this.address = address;
                this.imgURL = imgURL;
                this.balance = balance;
                this.enabled = enabled;
            }


            public Long getId() {
                return id;
            }

            public String getEmail() {
                return email;
            }

            public String getName() {
                return name;
            }

            public String getSurname() {
                return surname;
            }

            public String getPatronymic() {
                return patronymic;
            }

            public String getAge() {
                return age;
            }

            public String getGender() {
                return gender;
            }

            public Date getBirthdate() {
                return birthdate;
            }

            public String getTelephone() {
                return telephone;
            }

            public String getPassport() {
                return passport;
            }

            public String getAddress() {
                return address;
            }

            public String getImgURL() {
                return imgURL;
            }

            public Long getBalance() {
                return balance;
            }

            public Boolean getEnabled() {
                return enabled;
            }
        }




        public static class SubscriptionDto {
            private String name;

            private Date endDate;

            public SubscriptionDto() {
            }

            public SubscriptionDto(final String name, final Date endDate) {
                this.name = name;
                this.endDate = endDate;
            }

            public String getName() {
                return name;
            }

            public Date getEndDate() {
                return endDate;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setEndDate(Date endDate) {
                this.endDate = endDate;
            }
        }
    }
}