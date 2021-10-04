import React, { Component } from 'react'
import UserService from '../services/UserService';

export default class ListUserComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            users: []
        }
    }

    componentDidMount() {
        UserService.getUsers().then((res) => {
            this.setState({ users: res.data});
        });
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Users List</h2>
                <div className= "row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Id </th>
                                <th>Name</th>
                                <th>Age</th>
                                <th>Email</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.users.map(
                                    user => 
                                    <tr key={user.id}>
                                        <td>{user.id}</td>
                                        <td>{user.name}</td>
                                        <td>{user.age}</td>
                                        <td>{user.email}</td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}
