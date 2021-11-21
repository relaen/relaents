import {BaseEntity,Entity,Column,Id,JoinColumn,OneToOne,OneToMany,EntityProxy} from '../..';
import {User} from './user';
import {Shop} from './shop';

@Entity('t_user_info')
export class UserInfo extends BaseEntity{
	@Id()
	@Column({
		name:'user_id',
		type:'int',
		nullable:false
	})
	public userId:number;

	@OneToOne({entity:'User'})
	@JoinColumn({
		name:'user_id',
		refName:'user_id',
		nullable:true
	})
	public user:User;

	@Column({
		name:'real_name',
		type:'string',
		nullable:true,
		length:64
	})
	public realName:string;

	@Column({
		name:'age',
		type:'int',
		nullable:true
	})
	public age:number;

	@Column({
		name:'sexy',
		type:'string',
		nullable:true,
		length:1
	})
	public sexy:string;

	@Column({
		name:'remarks',
		type:'string',
		nullable:true,
		length:256
	})
	public remarks:string;

	@OneToMany({
		entity:'Shop',
		mappedBy:'userInfoForOwnerId'
	})
	public shopForOwnerIds:Array<Shop>;

	@OneToMany({
		entity:'Shop',
		mappedBy:'userInfoForManagerId'
	})
	public shopForManagerIds:Array<Shop>;

	constructor(idValue?:number){
		super();
		this.userId = idValue;
	}
	public async getUser():Promise<User>{
		return this['user']?this['user']:await EntityProxy.get(this,'user');
	}
	public async getShopForOwnerIds():Promise<Array<Shop>>{
		return this['shopForOwnerIds']?this['shopForOwnerIds']:await EntityProxy.get(this,'shopForOwnerIds');
	}
	public async getShopForManagerIds():Promise<Array<Shop>>{
		return this['shopForManagerIds']?this['shopForManagerIds']:await EntityProxy.get(this,'shopForManagerIds');
	}
}