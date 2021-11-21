import {BaseEntity,Entity,Column,Id,OneToMany,EntityProxy} from '../..';
import {GroupUser} from './groupuser';

@Entity('t_group')
export class Group extends BaseEntity{
	@Id()
	@Column({
		name:'group_id',
		type:'int',
		nullable:false,
		identity:true
	})
	public groupId:number;

	@Column({
		name:'group_name',
		type:'string',
		nullable:true,
		length:32
	})
	public groupName:string;

	@OneToMany({
		entity:'GroupUser',
		mappedBy:'group'
	})
	public groupUsers:Array<GroupUser>;

	constructor(idValue?:number){
		super();
		this.groupId = idValue;
	}
	public async getGroupUsers():Promise<Array<GroupUser>>{
		return this['groupUsers']?this['groupUsers']:await EntityProxy.get(this,'groupUsers');
	}
}