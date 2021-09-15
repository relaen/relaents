import {BaseEntity,Entity,Column,Id} from '../..';

@Entity('t_pk')
export class Pk extends BaseEntity{
	@Id()
	@Column({
		name:'id',
		type:'int',
		nullable:false
	})
	public id:number;

	@Column({
		name:'name',
		type:'string',
		nullable:true,
		length:255
	})
	public name:string;

	@Column({
		name:'value',
		type:'string',
		nullable:true,
		length:255
	})
	public value:string;

	constructor(idValue?:number){
		super();
		this.id = idValue;
	}
}