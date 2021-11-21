import {BaseEntity,Entity,Column,Id,OneToMany,EntityProxy} from '../..';
import {GroupUser} from './groupuser';
import {UserInfo} from './userinfo';

@Entity('t_user')
export class User extends BaseEntity{
	@Id()
	@Column({
		name:'user_id',
		type:'int',
		nullable:false,
		identity:true
	})
	public userId:number;

	@Column({
		name:'user_name',
		type:'string',
		nullable:true,
		length:32
	})
	public userName:string;

	@Column({
		name:'user_password',
		type:'string',
		nullable:true,
		length:32
	})
	public userPassword:string;

	@OneToMany({
		entity:'GroupUser',
		mappedBy:'user'
	})
	public groupUsers:Array<GroupUser>;

	@OneToMany({
		entity:'UserInfo',
		mappedBy:'user'
	})
	public userInfos:Array<UserInfo>;

	constructor(idValue?:number){
		super();
		this.userId = idValue;
	}
	public async getGroupUsers():Promise<Array<GroupUser>>{
		return this['groupUsers']?this['groupUsers']:await EntityProxy.get(this,'groupUsers');
	}
	public async getUserInfos():Promise<Array<UserInfo>>{
		return this['userInfos']?this['userInfos']:await EntityProxy.get(this,'userInfos');
	}
}