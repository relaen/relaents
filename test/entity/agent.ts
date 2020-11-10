
import {Area} from './area'
import {Member} from './member'
import {Tunnel} from './tunnel'
import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_agent",'tunnel')
export class Agent extends BaseEntity{
	@Id()
	@Column({
		name:'agent_id',
		type:'int',
		nullable:false
	})
	agentId:number;

	@ManyToOne({entity:Agent,lazyFetch:true})
	@JoinColumn({name:'area_id',refName:'area_id'})
	area:Area;

	@Column({
		name:'agent_name',
		type:'string',
		nullable:true
	})
	agentName:string;

	@Column({
		name:'manager',
		type:'string',
		nullable:true
	})
	manager:string;

	@Column({
		name:'manager_idno',
		type:'string',
		nullable:true
	})
	managerIdno:string;

	@Column({
		name:'contactor',
		type:'string',
		nullable:true
	})
	contactor:string;

	@Column({
		name:'manager_tel',
		type:'string',
		nullable:true
	})
	managerTel:string;

	@Column({
		name:'contat_tel',
		type:'string',
		nullable:true
	})
	contatTel:string;

	@Column({
		name:'address',
		type:'string',
		nullable:true
	})
	address:string;

	@Column({
		name:'email',
		type:'string',
		nullable:true
	})
	email:string;

	@Column({
		name:'zipcode',
		type:'string',
		nullable:true
	})
	zipcode:string;

	@OneToMany({entity:'Member',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'agent',lazyFetch:true})
	members:Array<Member>;

	@OneToMany({entity:'Tunnel',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'agent',lazyFetch:true})
	tunnels:Array<Tunnel>;

}